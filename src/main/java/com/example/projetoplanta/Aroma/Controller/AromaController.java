package com.example.projetoplanta.Aroma.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetoplanta.Aroma.DTO.AromaRequestDTO;
import com.example.projetoplanta.Aroma.DTO.AromaSelectedDTO;
import com.example.projetoplanta.Aroma.Mapper.AromaMapper;
import com.example.projetoplanta.Aroma.Module.AromaModel;
import com.example.projetoplanta.Aroma.Repository.AromaRepository;
import com.example.projetoplanta.exceptions.NotFoundException;

import jakarta.validation.Valid;

@RestController
public class AromaController {
    
    @Autowired
    AromaRepository aromaRepository;

    @GetMapping("/listar/aromas")
    public ResponseEntity<List<?>> listarTodos() {
        ResponseEntity<List<?>> responses;
        try {
            List<AromaModel> listaTodosaromas = aromaRepository.findAll();
            if (listaTodosaromas.isEmpty()) {
                throw new NotFoundException().toAroma();
            }
            List<AromaSelectedDTO> aromaSelectedDTOs = new ArrayList<>();
            for (AromaModel aroma : listaTodosaromas) {
                aromaSelectedDTOs.add(AromaMapper.toDTO(aroma));
                methodsOn(aroma);
            }
            responses = ResponseEntity.status(200).body(aromaSelectedDTOs);
        } catch (NotFoundException e) {
            responses = ResponseEntity.status(404).body(List.of(e.getMensagem()));
        } catch (Exception e) {
            responses = ResponseEntity.status(400).body(List.of("Erro ao listar todos aromas:: "+ e.getMessage()));
            // Logger.error RuntimeException("Erro ao listar todos aromas:: "+ e.getMessage());
        }
        return responses;
    }

    @GetMapping("/listar/aroma/{id}")
    public ResponseEntity<Object> listar(@PathVariable(value = "id") Integer id) {
        ResponseEntity<Object> response;
        try {
            Optional<AromaModel> optionalaroma = aromaRepository.findById(id);
            if (optionalaroma.isEmpty()) {
                throw new NotFoundException().toAroma(id);
            }
            AromaModel aroma = optionalaroma.get();
            methodsOn(aroma);
            response = ResponseEntity.status(200).body(AromaMapper.toDTO(aroma));
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao selecionar o aroma com o id:: "+id);
            // Logger.error RuntimeException("Erro ao selecionar o aroma com o id:: "+id+":: "+ e.getMessage());
        }
        return response;
    }

    @PostMapping("/def/aroma")
    public ResponseEntity<Object> defAroma(@RequestBody @Valid AromaRequestDTO aromaRequestDTO) {
        ResponseEntity<Object> response;
        try {
            AromaModel aroma = AromaMapper.toModel(aromaRequestDTO);
            methodsOn(aroma);
            aromaRepository.save(aroma);
            response = ResponseEntity.status(201).body(AromaMapper.toDTO(aroma));
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao definir aroma!");
            // Logger.error RuntimeException("Erro ao definir aroma:: "+ e.getMessage());
        }
        return response;
    }

    @PutMapping("/up/aroma/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable(value = "id") Integer id, @RequestBody @Valid AromaRequestDTO aromaRequestDTO) {
        ResponseEntity<Object> response;
        try {
            Optional<AromaModel> optionalAroma = aromaRepository.findById(id);
            if (optionalAroma.isEmpty()) {
                throw new NotFoundException().toAroma(id);
            }
            AromaModel aroma = AromaMapper.toModel(aromaRequestDTO);
            methodsOn(aroma);
            aroma = aromaRepository.save(aroma);
            response = ResponseEntity.status(200).body("Aroma:: "+aroma.getId()+" atualizado com sucesso!");
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao atualizar aroma.");
            // Logger.error RuntimeException("Erro ao deletar aroma:: "+ e.getMessage());
        }
        return response;
    }

    private void methodsOn(AromaModel aroma) {
        aroma.add(linkTo(methodOn(AromaController.class).atualizar(aroma.getId(), null)).withRel("atualizar"));        
        aroma.add(linkTo(methodOn(AromaController.class).listar(aroma.getId())).withRel("listar"));
        aroma.add(linkTo(methodOn(AromaController.class).listarTodos()).withRel("listarTodos"));
        aroma.add(linkTo(methodOn(AromaController.class).defAroma(null)).withRel("definirAroma"));
    }
}
