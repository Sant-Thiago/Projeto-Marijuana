package com.example.projetoplanta.com.example.projetoplanta.services;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projetoplanta.com.example.projetoplanta.DTO.UsuarioRecordDTO;
import com.example.projetoplanta.com.example.projetoplanta.exceptions.NotFoundException;
import com.example.projetoplanta.com.example.projetoplanta.modules.SolicitacaoModel;
import com.example.projetoplanta.com.example.projetoplanta.modules.UsuarioModel;
import com.example.projetoplanta.com.example.projetoplanta.repositories.SolicitacaoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class SolicitacaoService {
    
    @Autowired
    SolicitacaoRepository solicitacaoRepository;

    @PersistenceContext
    EntityManager entityManager;

    public String solicitarDuende(UsuarioModel solicitante, String motivo) {
        try {
            var status = entityManager.createQuery("SELECT status FROM SolicitacaoModel WHERE solicitante = :fkusuario AND tipo = :tipo")
                .setParameter("fkusuario", solicitante)
                .setParameter("tipo", "DUENDE")
                .getResultList();
            if (status.size() >= 3) return "A solicitação está em análise.";
            var solicitacaoModel = new SolicitacaoModel();
            solicitacaoModel.setSolicitante(solicitante);
            solicitacaoModel.setMotivo(motivo);
            solicitacaoModel.setTipo("DUENDE");
            solicitacaoModel.setStatus("PENDENTE");
            try {
                solicitacaoRepository.save(solicitacaoModel);
                return "Solicitação feita com sucesso.";
            } catch (Exception e) {
                throw new RuntimeException("Erro ao enviar a solicitação: "+ e.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro na query: "+e.getMessage());
        }
    }

    public Object solicitarModificacao(UsuarioRecordDTO usuarioRecordDTO, String id) {
        var solicitacaoModel = new SolicitacaoModel();
        UsuarioModel usuarioModel = DtoToModel(usuarioRecordDTO);
        solicitacaoModel.setSolicitante(usuarioModel);
        solicitacaoModel.setFotoUsuario(usuarioModel.getFoto());
        solicitacaoModel.setMotivo(null);
        solicitacaoModel.setTipo("FOTO");
        solicitacaoModel.setStatus("PENDENTE");
        try {
            solicitacaoRepository.save(solicitacaoModel);
            return "Solicitação de modificação do id: "+id+" feita com sucesso.";
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar a solicitação: "+ e.getMessage());
        }
    }

    private UsuarioModel DtoToModel(UsuarioRecordDTO usuarioRecordDTO) {
        var usuarioModel = new UsuarioModel();
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
