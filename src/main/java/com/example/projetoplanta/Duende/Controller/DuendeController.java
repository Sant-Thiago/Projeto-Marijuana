package com.example.projetoplanta.Duende.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetoplanta.Duende.DTO.DuendeRequestDTO;
import com.example.projetoplanta.Duende.Mapper.DuendeMapper;
import com.example.projetoplanta.Duende.Module.DuendeModel;
import com.example.projetoplanta.Duende.Repository.DuendeRepository;
import com.example.projetoplanta.exceptions.NotFoundException;

import jakarta.validation.Valid;

@RestController
public class DuendeController {
    
    @Autowired
    DuendeRepository duendeRepository;

    @Autowired
    DuendeMapper duendeMapper;

    @GetMapping("/listar/duendes")
    public ResponseEntity<List<DuendeModel>> listarTodos() {
        ResponseEntity<List<DuendeModel>> responses;
        try {
            List<DuendeModel> listaTodosDuendes = duendeRepository.findAll();
            if (listaTodosDuendes.isEmpty()) {
                throw new NotFoundException().toDuende();
            }
            for (DuendeModel duende : listaTodosDuendes) {
                methodsOn(duende);
            }
            responses = ResponseEntity.status(200).body(listaTodosDuendes);
        } catch (NotFoundException e) {
            responses = ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            responses = ResponseEntity.status(400).body(null);
            throw new RuntimeException("Erro ao listar todos duendes:: "+ e.getMessage());
        }
        return responses;
    }

    @GetMapping("/listar/duende/{id}")
    public ResponseEntity<Object> listar(@PathVariable(value = "id") Long id) {
        ResponseEntity<Object> response;
        try {
            Optional<DuendeModel> optionalDuende = duendeRepository.findById(id);
            if (optionalDuende.isEmpty()) {
                throw new NotFoundException().toDuende(id);
            }
            DuendeModel duende = optionalDuende.get();
            methodsOn(duende);
            response = ResponseEntity.status(200).body(duende);
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao selecionar o duende com o id:: "+id);
            throw new RuntimeException("Erro ao selecionar o duende com o id:: "+id+":: "+ e.getMessage());
        }
        return response;
    }

    @PostMapping("/def/duende")
    public ResponseEntity<Object> defDuende(@RequestBody @Valid DuendeRequestDTO duendeDTO) {
        ResponseEntity<Object> response;
        try {
            DuendeModel duende = duendeMapper.toModel(duendeDTO);
            duende = duendeRepository.save(duende);
            response = ResponseEntity.status(201).body(duendeMapper.toDTO(duende));
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao definir duende!");
            // Logger.error RuntimeException("Erro ao definir duende:: "+ e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/deletar/duende/{id}")
    public ResponseEntity<Object> deletar(@PathVariable(value = "id") Long id) {
        ResponseEntity<Object> response;
        try {
            Optional<DuendeModel> optionalDuende = duendeRepository.findById(id);
            if (optionalDuende.isEmpty()) {
                throw new NotFoundException().toDuende(id);
            }
            DuendeModel duende = optionalDuende.get();
            duendeRepository.delete(duende);
            response = ResponseEntity.status(200).body("Duende:: "+duende.getNumeroNacionalId()+" deletado com sucesso.");
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao deletar duende.");
            throw new RuntimeException("Erro ao deletar duende:: "+ e.getMessage());
        }
        return response;
    }

    private void methodsOn(DuendeModel duende) {
        duende.add(linkTo(methodOn(DuendeController.class).deletar(duende.getId())).withRel("deletar"));        
        duende.add(linkTo(methodOn(DuendeController.class).listar(duende.getId())).withRel("listar"));
        duende.add(linkTo(methodOn(DuendeController.class).listarTodos()).withRel("listarTodos"));
        duende.add(linkTo(methodOn(DuendeController.class).defDuende(null)).withRel("definirDuende"));
    }
}
