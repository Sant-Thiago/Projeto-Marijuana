package com.example.projetoplanta.com.example.projetoplanta.controllers;

import java.sql.Date;
import java.text.SimpleDateFormat;
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
import com.example.projetoplanta.com.example.projetoplanta.services.UsuarioService;
import com.example.projetoplanta.com.example.projetoplanta.exceptions.NotFoundException;
import com.example.projetoplanta.com.example.projetoplanta.services.linkTo.UsuarioLinkTo;

import jakarta.validation.Valid;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("/cadastrar/usuario")
    public ResponseEntity<Object> cadastrarUsuario(@RequestBody @Valid UsuarioRecordDTO usuario) {
        ResponseEntity<Object> response;
        try {
            var usuarioModel = new UsuarioModel();
            BeanUtils.copyProperties(usuario, usuarioModel);
            usuarioModel.setAtivo(true);
            usuarioModel.setDtNascimento(this.formatarData(usuarioModel.getDtNascimento()));

            usuarioRepository.save(usuarioModel);
            response = ResponseEntity.status(201).body("Usuário criado com sucesso.");
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Falha ao criar o usuário!");
            throw new RuntimeException("Erro ao criar usuário: "+ e.getMessage());
        }
        return response;
    }

    @GetMapping("/listar/usuarios")
    public ResponseEntity<List<UsuarioModel>> listarTodosUsuarios() {
        ResponseEntity<List<UsuarioModel>> responses;
        try {
            List<UsuarioModel> listaTodosUsuarios = usuarioRepository.findAll();
            if (listaTodosUsuarios.isEmpty()) {
                responses = ResponseEntity.status(404).body(null);
                throw new NotFoundException("Usuários não encontrados.");
            }
            for (UsuarioModel usuario : listaTodosUsuarios) {
                UsuarioLinkTo usuarioLinkTo = new UsuarioLinkTo(usuario);
                usuarioLinkTo.methodsOn();
            }
            responses = ResponseEntity.status(200).body(listaTodosUsuarios); 
        } catch (Exception e) {
            responses = ResponseEntity.status(400).body(null);
            throw new RuntimeException("Erro ao selecionar todos usuários: "+ e.getMessage());
        }
        return responses;
    }

    @GetMapping("/listar/usuario/{id}")
    public ResponseEntity<Object> listarUsuario(@PathVariable(value = "id") String id) {
        ResponseEntity<Object> response;
        try {
            Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
            if (usuario.isEmpty()) {
                response = ResponseEntity.status(404).body("Usuário com o id:: "+id+" não encontrado");
                throw new NotFoundException("Usuário com o id:: "+id+" não encontrado");
            }
            UsuarioLinkTo usuarioLinkTo = new UsuarioLinkTo(usuario.get());
            usuarioLinkTo.methodsOn();
            response = ResponseEntity.status(200).body(usuario);
        } catch (Exception e) {
            response = ResponseEntity.status(400).body(null);
            throw new RuntimeException("Erro ao selecionar o usuário com o id:: "+id+": "+ e.getMessage());
        }
        return response;
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
            Boolean status = usuarioService.ativoUsuario(id);
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

    
    private Date formatarData(Date data) {
        SimpleDateFormat formatoSaidaDF = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatadaStr = formatoSaidaDF.format(data);

        return Date.valueOf(dataFormatadaStr);
    }
}
