package com.example.projetoplanta.com.example.projetoplanta.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetoplanta.com.example.projetoplanta.DTO.DuendeRecordDTO;
import com.example.projetoplanta.com.example.projetoplanta.modules.DuendeModel;
import com.example.projetoplanta.com.example.projetoplanta.repositories.DuendeRepository;

import jakarta.validation.Valid;

@RestController
public class DuendeController {
    
    @Autowired
    DuendeRepository duendeRepository;

    @GetMapping("/listar/duendes")
    public ResponseEntity<List<DuendeModel>> listarTodosDuendes() {
        List<DuendeModel> listaTodosDuendes = duendeRepository.findAll();
        for (DuendeModel duende : listaTodosDuendes) {
            String id = duende.getFkUsuario();
            duende.add(linkTo(methodOn(DuendeController.class).listarDuende(id)).withRel("listarDuende"));
            duende.add(linkTo(methodOn(DuendeController.class).virarDuende(null)).withRel("virarDuende"));
            duende.add(linkTo(methodOn(DuendeController.class).deletarDuende(id)).withRel("deletar"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaTodosDuendes);
    }

    @GetMapping("/listar/duende/{id}")
    public ResponseEntity<Object> listarDuende(@PathVariable(value = "id") String id) {
        Optional<DuendeModel> duende = duendeRepository.findById(id);
        if (duende.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Duende não encontrado.");
        }    
        duende.get().add(linkTo(methodOn(DuendeController.class).listarTodosDuendes()).withRel("listarTodos"));
        duende.get().add(linkTo(methodOn(DuendeController.class).virarDuende(null)).withRel("virarDuende"));
        duende.get().add(linkTo(methodOn(DuendeController.class).deletarDuende(id)).withRel("deletar"));
        return ResponseEntity.status(HttpStatus.OK).body(duende.get());
    }

    @PostMapping("/virar/duende")
    public ResponseEntity<Object> virarDuende(@RequestBody @Valid DuendeRecordDTO duende) {
        var duendeModel = new DuendeModel();
        BeanUtils.copyProperties(duende, duendeModel);
        duendeRepository.save(duendeModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário " + duendeModel.getNumeroNascionalId() + ". Criado com sucesso.");
    }

    @DeleteMapping("deletar/duende/{id}")
    public ResponseEntity<Object> deletarDuende(@PathVariable(value = "id") String id) {
        Optional<DuendeModel> duende = duendeRepository.findById(id);
        if (duende.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Duende não encontrado.");
        }
        duendeRepository.delete(duende.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuário " + duende.get().getNumeroNascionalId() + ". Deletado com sucesso.");

    }
}
