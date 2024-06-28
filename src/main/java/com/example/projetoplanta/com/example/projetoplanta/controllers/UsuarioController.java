package com.example.projetoplanta.com.example.projetoplanta.controllers;

import java.util.List;
import java.util.Optional;

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
import com.example.projetoplanta.com.example.projetoplanta.services.UsuarioService;
import com.example.projetoplanta.com.example.projetoplanta.services.exceptions.NotFoundException;
import com.example.projetoplanta.com.example.projetoplanta.services.linkTo.UsuarioLinkTo;
import com.example.projetoplanta.com.example.projetoplanta.utils.Valid;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/cadastrar/usuario")
    public ResponseEntity<Object> cadastrarUsuario(@RequestBody @Valid UsuarioRecordDTO usuario) {

        if (!Valid.isEmail(usuario.email())) {
            throw ValidExe
        }
        usuarioService.cadastrarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso.");
    }

    @GetMapping("/listar/usuarios")
    public ResponseEntity<List<UsuarioModel>> listarTodosUsuarios() {
        List<UsuarioModel> listaTodosUsuarios = usuarioService.listarTodosUsuarios();
        for (UsuarioModel usuario : listaTodosUsuarios) {
            UsuarioLinkTo usuarioLinkTo = new UsuarioLinkTo(usuario);
            usuarioLinkTo.methodsOn();
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaTodosUsuarios);
    }

    @GetMapping("/listar/usuario/{id}")
    public ResponseEntity<Object> listarUsuario(@PathVariable(value = "id") String id) {
        Optional<UsuarioModel> usuario = usuarioService.listarUsuario(id);

        UsuarioLinkTo usuarioLinkTo = new UsuarioLinkTo(usuario.get());
        usuarioLinkTo.methodsOn();

        return ResponseEntity.status(HttpStatus.OK).body(usuario.get());
    }

    @PutMapping("/modificar/usuario/{id}")
    public ResponseEntity<Object> modificarUsuario(@PathVariable(value = "id") String id, @RequestBody @Valid UsuarioRecordDTO usuarioDTO) {
        try {
            usuarioService.modificarUsuario(id, usuarioDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Usuário modificado com sucesso.");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMensagem());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao modificar usuário.");
        }
    }

    @PutMapping("/status/usuario/{id}")
    public ResponseEntity<Object> statusUsuario(@PathVariable(value = "id") String id) {
        try {
            String status = usuarioService.statusUsuario(id);
            return ResponseEntity.status(HttpStatus.OK).body("Status do usuário modificado com sucesso: "+status);  
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMensagem());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao modificar usuário.");
        }
    }

    @DeleteMapping("/deletar/usuario/{id}")
    public ResponseEntity<Object> deletarUsuario(@PathVariable(value = "id") String id) {
        try {
            usuarioService.deletarUsuario(id);
            return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso.");
        } catch (NotFoundException e)  {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMensagem());
        } catch (Exception e ) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao modificar usuário.");
        }
    }

}
