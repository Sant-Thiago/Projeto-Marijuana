package com.example.projetoplanta.Solicitacao.Mapper;

import com.example.projetoplanta.Solicitacao.DTO.SolicitacaoResponseDTO;
import com.example.projetoplanta.Solicitacao.Module.SolicitacaoModel;

public class SolicitacaoMapper {
    
    public static SolicitacaoResponseDTO toDTO(SolicitacaoModel solicitacaoModel) {
        SolicitacaoResponseDTO solicitacaoResponseDTO = new SolicitacaoResponseDTO(
            solicitacaoModel.getId(),
            solicitacaoModel.getSolicitante(),
            solicitacaoModel.getTipo(),
            solicitacaoModel.getMotivo(),
            solicitacaoModel.getFotoUsuario(),
            solicitacaoModel.getDtArmazenamento(), 
            solicitacaoModel.getStatus()
        );
        return solicitacaoResponseDTO;
    }
}
