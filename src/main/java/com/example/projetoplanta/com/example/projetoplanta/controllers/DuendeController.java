package com.example.projetoplanta.com.example.projetoplanta.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetoplanta.com.example.projetoplanta.DTO.DuendeRecordDTO;
import com.example.projetoplanta.com.example.projetoplanta.exceptions.NotFoundException;
import com.example.projetoplanta.com.example.projetoplanta.modules.DuendeModel;
import com.example.projetoplanta.com.example.projetoplanta.repositories.DuendeRepository;

import jakarta.validation.Valid;

@RestController
public class DuendeController {
    
    @Autowired
    DuendeRepository duendeRepository;

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
    public ResponseEntity<Object> listar(@PathVariable(value = "id") String id) {
        ResponseEntity<Object> response;
        try {
            Optional<DuendeModel> optionalDuende = duendeRepository.findById(id);
            if (optionalDuende.isEmpty()) {
                throw new NotFoundException().toDuende();
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

    @PostMapping("/virar/duende")
    public ResponseEntity<Object> virarDuende(@RequestBody @Valid DuendeRecordDTO duende) {
        ResponseEntity<Object> response;
        try {
            var duendeModel = new DuendeModel();
            BeanUtils.copyProperties(duende, duendeModel);
            duendeRepository.save(duendeModel);
            response = ResponseEntity.status(201).body("Duende " + duendeModel.getNumeroNascionalId() + ". Criado com sucesso.");
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao virar duende!");
            throw new RuntimeException("Erro ao virar duende:: "+ e.getMessage());
        }
        return response;
    }

    @DeleteMapping("deletar/duende/{id}")
    public ResponseEntity<Object> deletar(@PathVariable(value = "id") String id) {
        ResponseEntity<Object> response;
        try {
            Optional<DuendeModel> optionalDuende = duendeRepository.findById(id);
            if (optionalDuende.isEmpty()) {
                throw new NotFoundException().toDuende(id);
            }
            DuendeModel duende = optionalDuende.get();
            duendeRepository.delete(duende);
            response = ResponseEntity.status(200).body("Duende:: "+duende.getNumeroNascionalId()+" deletado com sucesso.");
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao deletar duende.");
            throw new RuntimeException("Erro ao deletar duende:: "+ e.getMessage());
        }
        return response;
    }

    private void methodsOn(DuendeModel duende) {
        duende.add(linkTo(methodOn(DuendeController.class).deletar(duende.getFkUsuario().getId())).withRel("deletar"));        
        duende.add(linkTo(methodOn(DuendeController.class).listar(duende.getFkUsuario().getId())).withRel("listar"));
        duende.add(linkTo(methodOn(DuendeController.class).listarTodos()).withRel("listarTodos"));
        duende.add(linkTo(methodOn(DuendeController.class).virarDuende(null)).withRel("virarDuende"));
    }
}
