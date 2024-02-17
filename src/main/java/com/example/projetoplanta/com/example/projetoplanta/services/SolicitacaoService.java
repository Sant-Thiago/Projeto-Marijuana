package com.example.projetoplanta.com.example.projetoplanta.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.projetoplanta.com.example.projetoplanta.modules.SolicitacaoModel;
import com.example.projetoplanta.com.example.projetoplanta.modules.UsuarioModel;
import com.example.projetoplanta.com.example.projetoplanta.repositories.SolicitacaoRepository;

public class SolicitacaoService {
    
    @Autowired
    SolicitacaoRepository solicitacaoRepository;

    public void solicitarFoto(UsuarioModel usuarioModel, byte[] foto) {
        var solicitacaoModel = new SolicitacaoModel();
        solicitacaoModel.setSolicitante(usuarioModel);
        solicitacaoModel.setFotoUsuario(foto);
        solicitacaoModel.setMotivo(null);
        solicitacaoModel.setTipo("FOTO");
        solicitacaoModel.setStatus("PENDENTE");
        try {
            solicitacaoRepository.save(solicitacaoModel);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar a solicitação: "+ e.getMessage());
        }
    }
}
