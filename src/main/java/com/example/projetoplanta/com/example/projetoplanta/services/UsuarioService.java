package com.example.projetoplanta.com.example.projetoplanta.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projetoplanta.com.example.projetoplanta.DTO.UsuarioRecordDTO;
import com.example.projetoplanta.com.example.projetoplanta.exceptions.NotFoundException;
import com.example.projetoplanta.com.example.projetoplanta.modules.UsuarioModel;
import com.example.projetoplanta.com.example.projetoplanta.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    public void cadastrarUsuario(UsuarioRecordDTO usuarioRecordDTO) {
        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioRecordDTO, usuarioModel);
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
            throw new NotFoundException("Usuários não encontrado.");
        }
        return listaTodosUsuarios;
    }

    public Optional<UsuarioModel> listarUsuario(String id) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            throw new NotFoundException("Usuário com o id: "+id+" não encontrado.");
        }
        return usuario;
    }

    public void modificarUsuario(String id, UsuarioRecordDTO usuarioRecordDTO) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
            if (usuario.isEmpty()) {
                throw new NotFoundException("Usuário com o id: "+id+" não encontrado.");
            }
        var solicitacao = new SolicitacaoService();
        var usuarioModel = usuario.get();
        BeanUtils.copyProperties(usuarioRecordDTO, usuarioModel);
        try {
            if (usuarioModel.getFoto() != null) solicitacao.solicitarModificacao(usuarioRecordDTO, id);
            else {
                usuarioModel.setFoto(null);
            }
            usuarioRepository.save(usuarioModel);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar usuário: "+ e.getMessage());
        }
    }

    public String statusUsuario(String id) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            throw new NotFoundException("Usuário com o id: "+id+" não encontrado.");
        }
        var usuarioModel = usuario.get();
        if (usuarioModel.getStatus().equals("DESATIVADO")) usuarioModel.setStatus("ATIVO");
        else usuarioModel.setStatus("DESATIVADO");
        try {
            usuarioRepository.save(usuarioModel);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o status do usuário: "+ e.getMessage());
        }
        return usuarioModel.getStatus();
    }

    public void deletarUsuario(String id) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            throw new NotFoundException("Usuário com o id: "+id+" não encontrado.");
        }
        try {
            usuarioRepository.delete(usuario.get());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar usuário: "+ e.getMessage());
        }
    }
}
