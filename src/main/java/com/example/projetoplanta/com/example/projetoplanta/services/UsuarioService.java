package com.example.projetoplanta.com.example.projetoplanta.services;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.projetoplanta.com.example.projetoplanta.DTO.UsuarioRecordDTO;
import com.example.projetoplanta.com.example.projetoplanta.exceptions.NotFoundException;
import com.example.projetoplanta.com.example.projetoplanta.modules.UsuarioModel;
import com.example.projetoplanta.com.example.projetoplanta.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;




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

    public Boolean ativoUsuario(String id) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            throw new NotFoundException("Usuário com o id: "+id+" não encontrado.");
        }
        var usuarioModel = usuario.get();
        if (!usuarioModel.getAtivo()) usuarioModel.setAtivo(true);
        else usuarioModel.setAtivo(false);
        try {
            usuarioRepository.save(usuarioModel);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o status de ativo do usuário: "+ e.getMessage());
        }
        return usuarioModel.getAtivo();
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
