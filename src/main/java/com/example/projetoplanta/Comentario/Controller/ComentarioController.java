package com.example.projetoplanta.Comentario.Controller;

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

import com.example.projetoplanta.Comentario.DTO.ComentarioDTO;
import com.example.projetoplanta.Comentario.Mapper.ComentarioMapper;
import com.example.projetoplanta.Comentario.Module.ComentarioModel;
import com.example.projetoplanta.Comentario.Repository.ComentarioRepository;
import com.example.projetoplanta.exceptions.NotFoundException;

import jakarta.validation.Valid;

@RestController
public class ComentarioController {
    
    @Autowired
    ComentarioRepository comentarioRepository;

    @GetMapping("/listar/comentarios")
    public ResponseEntity<List<?>> listarTodos() {
        ResponseEntity<List<?>> responses;
        try {
            List<ComentarioModel> listaTodosefeitos = comentarioRepository.findAll();
            if (listaTodosefeitos.isEmpty()) {
                throw new NotFoundException().toComentario();
            }
            for (ComentarioModel comentario : listaTodosefeitos) {
                methodsOn(comentario);
            }
            responses = ResponseEntity.status(200).body(listaTodosefeitos);
        } catch (NotFoundException e) {
            responses = ResponseEntity.status(404).body(List.of(e.getMensagem()));
        } catch (Exception e) {
            responses = ResponseEntity.status(400).body(List.of("Erro ao listar todos comentarios:: "+ e.getMessage()));
            // Logger.error RuntimeException("Erro ao listar todos comentarios:: "+ e.getMessage());
        }
        return responses;
    }

    @GetMapping("/listar/comentario/{id}")
    public ResponseEntity<Object> listar(@PathVariable(value = "id") Integer id) {
        ResponseEntity<Object> response;
        try {
            Optional<ComentarioModel> optionalefeito = comentarioRepository.findById(id);
            if (optionalefeito.isEmpty()) {
                throw new NotFoundException().toComentario(id);
            }
            ComentarioModel comentario = optionalefeito.get();
            methodsOn(comentario);
            response = ResponseEntity.status(200).body(comentario);
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao selecionar o comentario com o id:: "+id);
            // Logger.error RuntimeException("Erro ao selecionar o comentario com o id:: "+id+":: "+ e.getMessage());
        }
        return response;
    }

    @PostMapping("/def/comentario")
    public ResponseEntity<Object> defEfeito(@RequestBody @Valid ComentarioDTO comentarioDTO) {
        ResponseEntity<Object> response;
        try {
            ComentarioModel ComentarioModel = ComentarioMapper.toModel(comentarioDTO);
            comentarioRepository.save(ComentarioModel);
            response = ResponseEntity.status(201).body("comentario " + ComentarioModel.getId() + ". Criado com sucesso.");
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao definir comentario!");
            // Logger.error RuntimeException("Erro ao definir comentario:: "+ e.getMessage());
        }
        return response;
    }

    @PutMapping("/up/comentario/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable(value = "id") Integer id, @RequestBody @Valid ComentarioDTO comentarioDTO) {
        ResponseEntity<Object> response;
        try {
            Optional<ComentarioModel> optionalefeito = comentarioRepository.findById(id);
            if (optionalefeito.isEmpty()) {
                throw new NotFoundException().toComentario(id);
            }
            ComentarioModel comentario = ComentarioMapper.toModel(comentarioDTO);
            comentario = comentarioRepository.save(comentario);
            response = ResponseEntity.status(200).body("Comentario:: "+comentario.getId()+" atualizado com sucesso!");
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao atualizar comentario.");
            // Logger.error RuntimeException("Erro ao deletar comentario:: "+ e.getMessage());
        }
        return response;
    }

    private void methodsOn(ComentarioModel comentario) {
        comentario.add(linkTo(methodOn(ComentarioController.class).atualizar(comentario.getId(), null)).withRel("atualizar"));        
        comentario.add(linkTo(methodOn(ComentarioController.class).listar(comentario.getId())).withRel("listar"));
        comentario.add(linkTo(methodOn(ComentarioController.class).listarTodos()).withRel("listarTodos"));
        comentario.add(linkTo(methodOn(ComentarioController.class).defEfeito(null)).withRel("definirComentario"));
    }
}
