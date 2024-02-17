package com.example.projetoplanta.com.example.projetoplanta.services;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projetoplanta.com.example.projetoplanta.DTO.UsuarioRecordDTO;
import com.example.projetoplanta.com.example.projetoplanta.modules.SolicitacaoModel;
import com.example.projetoplanta.com.example.projetoplanta.modules.UsuarioModel;
import com.example.projetoplanta.com.example.projetoplanta.repositories.SolicitacaoRepository;
import com.example.projetoplanta.com.example.projetoplanta.services.exceptions.DadoNaoEncontradoException;

@Service
public class SolicitacaoService {
    
    @Autowired
    SolicitacaoRepository solicitacaoRepository;

    public List<SolicitacaoModel> listarSolicitacoes() {
        var listaSolicitacao = solicitacaoRepository.findAll();
        if (listaSolicitacao.isEmpty()) {
            throw new DadoNaoEncontradoException("Nenhuma solicitação encontrada");
        }
        return listaSolicitacao;
    }

    public void solicitarDuende(UsuarioRecordDTO solicitanteDTO, String motivo) {
        var solicitacaoModel = new SolicitacaoModel();
        UsuarioModel solicitanteModel = DtoToModel(solicitanteDTO);
        solicitacaoModel.setSolicitante(solicitanteModel);
        solicitacaoModel.setMotivo(motivo);
        solicitacaoModel.setTipo("DUENDE");
        solicitacaoModel.setStatus("PENDENTE");
        try {
            solicitacaoRepository.save(solicitacaoModel);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar a solicitação: "+ e.getMessage());
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
