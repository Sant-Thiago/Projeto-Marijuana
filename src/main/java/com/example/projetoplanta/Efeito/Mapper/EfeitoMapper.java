package com.example.projetoplanta.Efeito.Mapper;

import com.example.projetoplanta.Efeito.DTO.EfeitoRequestDTO;
import com.example.projetoplanta.Efeito.DTO.EfeitoSelectedDTO;
import com.example.projetoplanta.Efeito.Module.EfeitoModel;

public class EfeitoMapper {
    
    public static EfeitoModel toModel(EfeitoRequestDTO efeitoRequestDTO) {
        EfeitoModel efeitoModel = new EfeitoModel();
        efeitoModel.setTipo(efeitoRequestDTO.tipo());
        efeitoModel.setNome(efeitoRequestDTO.nome());
        efeitoModel.setCausa(efeitoRequestDTO.causa());
        return efeitoModel;
    }

    public static EfeitoSelectedDTO toDTO(EfeitoModel efeitoModel) {
        EfeitoSelectedDTO efeitoSelectedDTO = new EfeitoSelectedDTO(
            efeitoModel.getId(),
            efeitoModel.getTipo(),
            efeitoModel.getNome(),
            efeitoModel.getCausa()
        );
        return efeitoSelectedDTO;
    }
}
