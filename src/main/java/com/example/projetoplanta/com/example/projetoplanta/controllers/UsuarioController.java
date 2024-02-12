package com.example.projetoplanta.com.example.projetoplanta.controllers;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetoplanta.com.example.projetoplanta.DTO.UsuarioRecordDTO;
import com.example.projetoplanta.com.example.projetoplanta.modules.UsuarioModel;
import com.example.projetoplanta.com.example.projetoplanta.repositories.UsuarioRepository;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("/usuario")
    public ResponseEntity<UsuarioModel> cadastrarUsuario(@RequestBody @Valid UsuarioRecordDTO usuario) {
        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuario, usuarioModel);
        usuarioModel.setStatus("ATIVO");
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuarioModel));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listarTodosUsuarios() {
        List<UsuarioModel> listaTodosUsuarios = usuarioRepository.findAll();
        if (!listaTodosUsuarios.isEmpty()) {
            for (UsuarioModel usuario : listaTodosUsuarios) {
                String id = usuario.getId();
                usuario.add(linkTo(methodOn(UsuarioController.class).listarUsuario(id)).withSelfRel());
                usuario.add(linkTo(methodOn(UsuarioController.class, ativarUsuario(id))).withSelfRel());
                usuario.add(linkTo(methodOn(UsuarioController.class, desativarUsuario(id))).withSelfRel());
                usuario.add(linkTo(methodOn(UsuarioController.class, deletarUsuario(id))).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaTodosUsuarios);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Object> listarUsuario(@PathVariable(value = "id") String id) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
        }
        usuario.get().add(linkTo(methodOn(UsuarioController.class).listarTodosUsuarios()).withSelfRel());
        usuario.get().add(linkTo(methodOn(UsuarioController.class, ativarUsuario(id))).withSelfRel());
        usuario.get().add(linkTo(methodOn(UsuarioController.class, desativarUsuario(id))).withSelfRel());
        usuario.get().add(linkTo(methodOn(UsuarioController.class, deletarUsuario(id))).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(usuario.get());
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<Object> ativarUsuario(@PathVariable(value = "id") String id) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
        }
        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuario.get(), usuarioModel);
        usuarioModel.setStatus("ATIVADO");
        return ResponseEntity.status(HttpStatus.OK).body("Usuario atualizado: " + usuarioRepository.save(usuarioModel));
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<Object> desativarUsuario(@PathVariable(value = "id") String id) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
        }
        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuario.get(), usuarioModel);
        usuarioModel.setStatus("DESATIVADO");
        return ResponseEntity.status(HttpStatus.OK).body("Usuario atualizado: " + usuarioRepository.save(usuarioModel));
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<Object> deletarUsuario(@PathVariable(value = "id") String id) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
        }
        usuarioRepository.delete(usuario.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso.");
        
    }
}
