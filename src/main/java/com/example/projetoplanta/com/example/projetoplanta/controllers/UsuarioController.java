package com.example.projetoplanta.com.example.projetoplanta.controllers;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid UsuarioRecordDTO usuario) {
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
            throw new RuntimeException("Erro ao criar usuário:: "+ e.getMessage());
        }
        return response;
    }

    @GetMapping("/listar/usuarios")
    public ResponseEntity<List<UsuarioModel>> listarTodos() {
        ResponseEntity<List<UsuarioModel>> responses;
        try {
            List<UsuarioModel> listaTodosUsuarios = usuarioRepository.findAll();
            if (listaTodosUsuarios.isEmpty()) {
                throw new NotFoundException("Usuários não encontrados.");
            }
            for (UsuarioModel usuario : listaTodosUsuarios) {
                methodsOn(usuario);
            }
            responses = ResponseEntity.status(200).body(listaTodosUsuarios);
        } catch(NotFoundException e) {
            responses = ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            responses = ResponseEntity.status(400).body(null);
            throw new RuntimeException("Erro ao listar todos usuários:: "+ e.getMessage());
        }
        return responses;
    }

    @GetMapping("/listar/usuario/{id}")
    public ResponseEntity<Object> listar(@PathVariable(value = "id") String id) {
        ResponseEntity<Object> response;
        try {
            Optional<UsuarioModel> optionaUsuario = usuarioRepository.findById(id);
            if (optionaUsuario.isEmpty()) {
                throw new NotFoundException("Usuário com o id:: "+id+" não encontrado");
            }
            UsuarioModel usuario = optionaUsuario.get();
            
            UsuarioLinkTo usuarioLinkTo = new UsuarioLinkTo(usuario);
            usuarioLinkTo.methodsOn();
            response = ResponseEntity.status(200).body(usuario);
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
        } catch (Exception e) {
            response = ResponseEntity.status(400).body(null);
            throw new RuntimeException("Erro ao selecionar o usuário com o id:: "+id+": "+ e.getMessage());
        }
        return response;
    }

    @PutMapping("/modificar/usuario/{id}")
    public ResponseEntity<Object> modificarUsuario(@PathVariable(value = "id") String id, @RequestBody @Valid UsuarioRecordDTO usuarioDTO) {
        ResponseEntity<Object> response;
        try {
            Optional<UsuarioModel> optionalUsuario = usuarioRepository.findById(id);
            if (optionalUsuario.isEmpty()) {
                throw new NotFoundException("Usuário com o id:: "+id+" não encontrado.");
            }
            UsuarioModel usuario = optionalUsuario.get()
            BeanUtils.copyProperties(usuarioDTO, usuario);
            
            if (usuario.getFoto() != null) {
                var solicitacaoController = new SolicitacaoController();
                solicitacaoController.solicitarImagem(usuario, id);
            }
            usuarioRepository.save(usuario);
            response =  ResponseEntity.status(200).body("Usuário modificado com sucesso.");
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
        } catch (Exception e) {
            response = ResponseEntity.status(500).body("Erro ao modificar usuário.");
            throw new RuntimeException("Erro ao modificar usuário:: "+ e.getMessage());
        }
        return response;
    }

    @PutMapping("/status/usuario/{id}")
    public ResponseEntity<Object> status(@PathVariable(value = "id") String id) {
        ResponseEntity<Object> response;
        try {
            Optional<UsuarioModel> optionalUsuario = usuarioRepository.findById(id);
            if (optionalUsuario.isEmpty()) {
                throw new NotFoundException("Usuário com o id:: "+id+" não encontrado.");
            }
            UsuarioModel usuario = optionalUsuario.get();
            if (!usuario.getAtivo()) {
                usuario.setAtivo(true);
            } else {
                usuario.setAtivo(false);
            }
            usuarioRepository.save(usuario);
            response = ResponseEntity.status(200).body("Status do usuário modificado com sucesso:: "+ usuario.getAtivo());  
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
        } catch (Exception e) {
            response = ResponseEntity.status(500).body("Erro ao modificar o status de atividade do usuário.");
            throw new RuntimeException("Erro ao modificar o status de atividade do usuário:: "+ e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/deletar/usuario/{id}")
    public ResponseEntity<Object> deletar(@PathVariable(value = "id") String id) {
        ResponseEntity<Object> response;
        try {
            Optional<UsuarioModel> optionalUsuario = usuarioRepository.findById(id);
            if (optionalUsuario.isEmpty()) {
                throw new NotFoundException("Usuário com o id:: "+id+" não encontrado.");
            }
            response = ResponseEntity.status(200).body("Usuário deletado com sucesso.");
        } catch (NotFoundException e)  {
            response = ResponseEntity.status(404).body(e.getMensagem());
        } catch (Exception e ) {
            response = ResponseEntity.status(500).body("Erro ao deletar usuário.");
            throw new RuntimeException("Erro ao deletar usuário:: "+ e.getMessage());
        }
        return response;
    }


    private void methodsOn(UsuarioModel usuario) {
        usuario.add(linkTo(methodOn(UsuarioController.class).deletarUsuario(usuario.getId())).withRel("deletar"));
        usuario.add(linkTo(methodOn(UsuarioController.class).listarTodosUsuarios()).withRel("listarTodos"));
        usuario.add(linkTo(methodOn(UsuarioController.class).listarUsuario(usuario.getId())).withRel("listar"));
        usuario.add(linkTo(methodOn(UsuarioController.class).modificarUsuario(usuario.getId(), null)).withRel("modificar"));
        usuario.add(linkTo(methodOn(UsuarioController.class).statusUsuario(usuario.getId())).withRel("ativar"));
    }
    
    private Date formatarData(Date data) {
        SimpleDateFormat formatoSaidaDF = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatadaStr = formatoSaidaDF.format(data);

        return Date.valueOf(dataFormatadaStr);
    }
}
