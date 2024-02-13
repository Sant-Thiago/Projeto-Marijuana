package com.example.projetoplanta.com.example.projetoplanta.controllers;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.projetoplanta.com.example.projetoplanta.DTO.UsuarioRecordDTO;
import com.example.projetoplanta.com.example.projetoplanta.modules.UsuarioModel;
import com.example.projetoplanta.com.example.projetoplanta.repositories.UsuarioRepository;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("/cadastrar")
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
                usuario.add(linkTo(methodOn(UsuarioController.class).listarUsuario(id)).withRel("listar"));
                usuario.add(linkTo(methodOn(UsuarioController.class).ativarUsuario(id)).withRel("ativar"));
                usuario.add(linkTo(methodOn(UsuarioController.class).desativarUsuario(id)).withRel("desativar"));
                usuario.add(linkTo(methodOn(UsuarioController.class).alterarFoto(id, null)).withRel("alterar"));
                usuario.add(linkTo(methodOn(UsuarioController.class).deletarUsuario(id)).withRel("deletar"));
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaTodosUsuarios);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Object> listarUsuario(@PathVariable(value = "id") String id) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
        usuario.get().add(linkTo(methodOn(UsuarioController.class).listarTodosUsuarios()).withRel("listarTodos"));
        usuario.get().add(linkTo(methodOn(UsuarioController.class).ativarUsuario(id)).withRel("ativar"));
        usuario.get().add(linkTo(methodOn(UsuarioController.class).desativarUsuario(id)).withRel("desativar"));
        usuario.get().add(linkTo(methodOn(UsuarioController.class).alterarFoto(id, null)).withRel("alterar"));
        usuario.get().add(linkTo(methodOn(UsuarioController.class).deletarUsuario(id)).withRel("deletar"));
        return ResponseEntity.status(HttpStatus.OK).body(usuario.get());
    }

    

    @PutMapping("/ativar/{id}")
    public ResponseEntity<Object> ativarUsuario(@PathVariable(value = "id") String id) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuario.get(), usuarioModel);
        usuarioModel.setStatus("ATIVADO");
        return ResponseEntity.status(HttpStatus.OK).body("Usuário atualizado: " + usuarioRepository.save(usuarioModel));
    }

    @PutMapping("/desativar/{id}")
    public ResponseEntity<Object> desativarUsuario(@PathVariable(value = "id") String id) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuario.get(), usuarioModel);
        usuarioModel.setStatus("DESATIVADO");
        return ResponseEntity.status(HttpStatus.OK).body("Usuário atualizado: " + usuarioRepository.save(usuarioModel));
    }

    @PutMapping("/imagem/{id}")
    public ResponseEntity<Object> alterarFoto(@PathVariable(value = "id") String id, @RequestParam("foto") MultipartFile foto) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
        var usuarioModel = usuario.get();
        try {
            if (foto == null) {
                usuarioModel.setFoto(null);
                return ResponseEntity.status(HttpStatus.OK).body("Imagem foi anulada" + usuarioRepository.save(usuarioModel));
            }
            usuarioModel.setFoto(foto.getBytes());
            // PENDENCIAS
            return ResponseEntity.status(HttpStatus.OK).body("Usuário atualizado: " + usuarioRepository.save(usuarioModel));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a imagem.");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Object> deletarUsuario(@PathVariable(value = "id") String id) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
        usuarioRepository.delete(usuario.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso.");
    }
}
