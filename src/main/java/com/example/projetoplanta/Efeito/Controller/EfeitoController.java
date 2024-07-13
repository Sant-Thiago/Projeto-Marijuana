package com.example.projetoplanta.Efeito.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

import com.example.projetoplanta.Efeito.DTO.EfeitoDTO;
import com.example.projetoplanta.Efeito.Mapper.EfeitoMapper;
import com.example.projetoplanta.Efeito.Module.EfeitoModel;
import com.example.projetoplanta.Efeito.Repository.EfeitoRepository;
import com.example.projetoplanta.exceptions.NotFoundException;

import jakarta.validation.Valid;

@RestController
public class EfeitoController {
    
    @Autowired
    EfeitoRepository efeitoRepository;

    @GetMapping("/listar/efeitos")
    public ResponseEntity<List<?>> listarTodos() {
        ResponseEntity<List<?>> responses;
        try {
            List<EfeitoModel> listaTodosefeitos = efeitoRepository.findAll();
            if (listaTodosefeitos.isEmpty()) {
                throw new NotFoundException().toEfeito();
            }
            for (EfeitoModel efeito : listaTodosefeitos) {
                methodsOn(efeito);
            }
            responses = ResponseEntity.status(200).body(listaTodosefeitos);
        } catch (NotFoundException e) {
            responses = ResponseEntity.status(404).body(List.of(e.getMensagem()));
        } catch (Exception e) {
            responses = ResponseEntity.status(400).body(List.of("Erro ao listar todos efeitos:: "+ e.getMessage()));
            // Logger.error RuntimeException("Erro ao listar todos efeitos:: "+ e.getMessage());
        }
        return responses;
    }

    @GetMapping("/listar/efeito/{id}")
    public ResponseEntity<Object> listar(@PathVariable(value = "id") Integer id) {
        ResponseEntity<Object> response;
        try {
            Optional<EfeitoModel> optionalefeito = efeitoRepository.findById(id);
            if (optionalefeito.isEmpty()) {
                throw new NotFoundException().toEfeito(id);
            }
            EfeitoModel efeito = optionalefeito.get();
            methodsOn(efeito);
            response = ResponseEntity.status(200).body(efeito);
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao selecionar o efeito com o id:: "+id);
            // Logger.error RuntimeException("Erro ao selecionar o efeito com o id:: "+id+":: "+ e.getMessage());
        }
        return response;
    }

    @PostMapping("/def/efeito")
    public ResponseEntity<Object> defEfeito(@RequestBody @Valid EfeitoDTO efeitoDTO) {
        ResponseEntity<Object> response;
        try {
            EfeitoModel efeitoModel = EfeitoMapper.toModel(efeitoDTO);
            efeitoRepository.save(efeitoModel);
            response = ResponseEntity.status(201).body("Efeito " + efeitoModel.getId() + ". Criado com sucesso.");
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao definir efeito!");
            // Logger.error RuntimeException("Erro ao definir efeito:: "+ e.getMessage());
        }
        return response;
    }

    @PutMapping("/up/efeito/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable(value = "id") Integer id, @RequestBody @Valid EfeitoDTO efeitoDTO) {
        ResponseEntity<Object> response;
        try {
            Optional<EfeitoModel> optionalefeito = efeitoRepository.findById(id);
            if (optionalefeito.isEmpty()) {
                throw new NotFoundException().toEfeito(id);
            }
            EfeitoModel efeito = EfeitoMapper.toModel(efeitoDTO);
            efeito = efeitoRepository.save(efeito);
            response = ResponseEntity.status(200).body("Efeito:: "+efeito.getId()+" atualizado com sucesso!");
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao atualizar efeito.");
            // Logger.error RuntimeException("Erro ao deletar efeito:: "+ e.getMessage());
        }
        return response;
    }

    private void methodsOn(EfeitoModel efeito) {
        efeito.add(linkTo(methodOn(EfeitoController.class).atualizar(efeito.getId(), null)).withRel("atualizar"));        
        efeito.add(linkTo(methodOn(EfeitoController.class).listar(efeito.getId())).withRel("listar"));
        efeito.add(linkTo(methodOn(EfeitoController.class).listarTodos()).withRel("listarTodos"));
        efeito.add(linkTo(methodOn(EfeitoController.class).defEfeito(null)).withRel("definirEfeito"));
    }
}
