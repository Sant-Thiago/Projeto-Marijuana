package com.example.projetoplanta.Usuario.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetoplanta.Usuario.DTO.UsuarioRecordDTO;
import com.example.projetoplanta.Usuario.Module.UsuarioModel;
import com.example.projetoplanta.Usuario.Repository.UsuarioRepository;
import com.example.projetoplanta.exceptions.NotFoundException;

import jakarta.validation.Valid;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("/cadastrar/usuario")
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid UsuarioRecordDTO usuario) {
        ResponseEntity<Object> response;
        try {
            var usuarioModel = new UsuarioModel();
            BeanUtils.copyProperties(usuario, usuarioModel);
            usuarioModel.setAtivo(true);
            // usuarioModel.setDtNascimento(this.formatarData(usuarioModel.getDtNascimento()));

            UsuarioModel usuarioSaved = usuarioRepository.save(usuarioModel);
            response = ResponseEntity.status(201).body(usuarioSaved);
            // Logger.saved("Usuário "+usuario.getId()+" criado com sucesso.");
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao criar o usuário!");
            // Logger.error("Erro ao criar usuário!\n\n"+ e.getMessage());
        }
        return response;
    }

    @GetMapping("/listar/usuarios")
    public ResponseEntity<List<UsuarioModel>> listarTodos() {
        ResponseEntity<List<UsuarioModel>> responses;
        try {
            List<UsuarioModel> listaTodosUsuarios = usuarioRepository.findAll();
            if (listaTodosUsuarios.isEmpty()) {
                throw new NotFoundException().toUsuario();
            }
            for (UsuarioModel usuario : listaTodosUsuarios) {
                methodsOn(usuario);
            }
            responses = ResponseEntity.status(200).body(listaTodosUsuarios);
        } catch(NotFoundException e) {
            responses = ResponseEntity.status(404).body(null);
            // Logger.notFound("Nenhum dado encontrado no sistema!");
        } catch (Exception e) {
            responses = ResponseEntity.status(400).body(null);
            // Logger.error("Erro ao listar todos usuários!\n\n"+ e.getMessage());
        }
        return responses;
    }

    @GetMapping("/listar/usuario/{id}")
    public ResponseEntity<Object> listar(@PathVariable(value = "id") String id) {
        ResponseEntity<Object> response;
        try {
            Optional<UsuarioModel> optionaUsuario = usuarioRepository.findById(id);
            if (optionaUsuario.isEmpty()) {
                throw new NotFoundException().toUsuario(id);
            }
            UsuarioModel usuario = optionaUsuario.get();
            methodsOn(usuario);
            response = ResponseEntity.status(200).body(usuario);
            // Logger.select("Usuário "+usuario.getId()+" selecionado com sucesso.");
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
            // Logger.notFound("Usuário "+usuario.getId()+" não encontrado no sistema!");
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao selecionar o usuário com o id:: "+id);
            // Logger.error("Erro ao selecionar o usuário com o id:: "+id+" no sistema!\n\n"+ e.getMessage());
        }
        return response;
    }

    @PutMapping("/modificar/usuario/{id}")
    public ResponseEntity<Object> modificar(@PathVariable(value = "id") String id, @RequestBody @Valid UsuarioRecordDTO usuarioDTO) {
        ResponseEntity<Object> response;
        try {
            Optional<UsuarioModel> optionalUsuario = usuarioRepository.findById(id);
            if (optionalUsuario.isEmpty()) {
                throw new NotFoundException().toUsuario(id);
            }
            UsuarioModel usuario = optionalUsuario.get();
            BeanUtils.copyProperties(usuarioDTO, usuario);
            usuarioRepository.save(usuario);
            response =  ResponseEntity.status(200).body("Usuário modificado com sucesso.");
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
            // Logger.notFound("Usuário "+usuario.getId()+" não encontrado no sistema!");
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao modificar usuário.");
            // Logger.error("Erro ao modificar o usuário com o id:: "+id+" no sistema!\n\n"+ e.getMessage());
        }
        return response;
    }

    @PutMapping("/status/usuario/{id}")
    public ResponseEntity<Object> modificarStatus(@PathVariable(value = "id") String id) {
        ResponseEntity<Object> response;
        try {
            Optional<UsuarioModel> optionalUsuario = usuarioRepository.findById(id);
            if (optionalUsuario.isEmpty()) {
                throw new NotFoundException().toUsuario(id);
            }
            UsuarioModel usuario = optionalUsuario.get();
            if (usuario.getAtivo()) {
                usuario.setAtivo(false);
            } else {
                usuario.setAtivo(true);
            }
            usuarioRepository.save(usuario);
            response = ResponseEntity.status(200).body("Status do usuário modificado com sucesso:: "+ usuario.getAtivo());  
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
            // Logger.notFound("Usuário "+usuario.getId()+" não encontrado no sistema!");
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao modificar o status de atividade do usuário.");
            // Logger.error("Erro ao modificar o status de atividade do usuário com o id:: "+id+" no sistema!\n\n"+ e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/deletar/usuario/{id}")
    public ResponseEntity<Object> deletar(@PathVariable(value = "id") String id) {
        ResponseEntity<Object> response;
        try {
            Optional<UsuarioModel> optionalUsuario = usuarioRepository.findById(id);
            if (optionalUsuario.isEmpty()) {
                throw new NotFoundException().toUsuario(id);
            }
            response = ResponseEntity.status(200).body("Usuário deletado com sucesso.");
        } catch (NotFoundException e)  {
            response = ResponseEntity.status(404).body(e.getMensagem());
            // Logger.notFound("Usuário "+usuario.getId()+" não encontrado no sistema!");
        } catch (Exception e ) {
            response = ResponseEntity.status(400).body("Erro ao deletar usuário.");
            // Logger.error("Erro ao deletar o usuário com o id:: "+id+" no sistema!\n\n"+ e.getMessage());
        }
        return response;
    }


    private void methodsOn(UsuarioModel usuario) {
        usuario.add(linkTo(methodOn(UsuarioController.class).cadastrar(null)).withRel("cadastrar"));
        usuario.add(linkTo(methodOn(UsuarioController.class).deletar(usuario.getId())).withRel("deletar"));
        usuario.add(linkTo(methodOn(UsuarioController.class).listarTodos()).withRel("listarTodos"));
        usuario.add(linkTo(methodOn(UsuarioController.class).listar(usuario.getId())).withRel("listar"));
        usuario.add(linkTo(methodOn(UsuarioController.class).modificar(usuario.getId(), null)).withRel("modificar"));
        usuario.add(linkTo(methodOn(UsuarioController.class).modificarStatus(usuario.getId())).withRel("ativar"));
    }
    
    private Date formatarData(Date data) {
        SimpleDateFormat formatoSaidaDF = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatadaStr = formatoSaidaDF.format(data);

        return Date.valueOf(dataFormatadaStr);
    }
}
