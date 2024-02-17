package com.example.projetoplanta.com.example.projetoplanta.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.projetoplanta.com.example.projetoplanta.DTO.UsuarioRecordDTO;
import com.example.projetoplanta.com.example.projetoplanta.controllers.UsuarioController;
import com.example.projetoplanta.com.example.projetoplanta.exceptions.UsuarioNaoEncontradoException;
import com.example.projetoplanta.com.example.projetoplanta.modules.UsuarioModel;
import com.example.projetoplanta.com.example.projetoplanta.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    SolicitacaoService solicitacao;

    public void cadastrarUsuario(UsuarioRecordDTO usuarioRecordDTO) {
        var usuarioModel = new UsuarioModel();
        DtoToModel(usuarioRecordDTO, usuarioModel);
        usuarioModel.setStatus("ATIVO");
        try {
            usuarioRepository.save(usuarioModel);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar usuário: "+ e.getMessage());
        }
    }

    public List<UsuarioModel> listarTodosUsuarios() {
        List<UsuarioModel> listaTodosUsuarios = usuarioRepository.findAll();
        if (listaTodosUsuarios.isEmpty()) {
            throw new UsuarioNaoEncontradoException("Usuários não encontrado.");
        }
        return listaTodosUsuarios;
    }

    public Optional<UsuarioModel> listarUsuario(String id) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            throw new UsuarioNaoEncontradoException("Usuário com o id: "+id+" não encontrado.");
        }
        return usuario;
    }

    public void modificarUsuario(String id, UsuarioRecordDTO usuarioRecordDTO) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
            if (usuario.isEmpty()) {
                throw new UsuarioNaoEncontradoException("Usuário com o id: "+id+" não encontrado.");
            }
            var usuarioModel = usuario.get();
            DtoToModel(usuarioRecordDTO, usuarioModel);
            if (usuarioModel.getFoto() != null) solicitacao.solicitarFoto(usuarioModel, usuarioModel.getFoto());
            try {
                usuarioRepository.save(usuarioModel);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao atualizar usuário: "+ e.getMessage());
            }
    }

    public void alterarFoto(String id, MultipartFile foto) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
            if (usuario.isEmpty()) {
                throw new UsuarioNaoEncontradoException("Usuário com o id: "+id+" não encontrado.");
            }
        var usuarioModel = usuario.get();
        try {
            if (foto == null) {
                usuarioModel.setFoto(null);
                usuarioRepository.save(usuarioModel);
            } else {
                usuarioModel.setFoto(foto.getBytes());
                solicitacao.solicitarFoto(usuarioModel, foto.getBytes());            
                usuarioRepository.save(usuarioModel);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao alterar a foto do usuário: "+ e.getMessage());
        }
    }

    public void statusUsuario(String id) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            throw new UsuarioNaoEncontradoException("Usuário com o id: "+id+" não encontrado.");
        }
        var usuarioModel = usuario.get();
        if (usuarioModel.getStatus().equals("DESATIVADO")) usuarioModel.setStatus("ATIVADO");
        else usuarioModel.setStatus("DESATIVADO");
        try {
            usuarioRepository.save(usuarioModel);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o status do usuário: "+ e.getMessage());
        }
    }

    public void deletarUsuario(String id) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            throw new UsuarioNaoEncontradoException("Usuário com o id: "+id+" não encontrado.");
        }
        try {
            usuarioRepository.delete(usuario.get());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar usuário: "+ e.getMessage());
        }
    }

    private UsuarioModel DtoToModel(UsuarioRecordDTO usuarioRecordDTO, UsuarioModel usuarioModel) {
        for (Field field : usuarioRecordDTO.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                usuarioModel.getClass().getDeclaredField(field.getName()).set(usuarioModel, field.get(usuarioRecordDTO));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return usuarioModel;
    }
}
