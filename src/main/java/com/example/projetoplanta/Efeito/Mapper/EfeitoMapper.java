package com.example.projetoplanta.Efeito.Mapper;

import com.example.projetoplanta.Efeito.DTO.EfeitoDTO;
import com.example.projetoplanta.Efeito.Module.EfeitoModel;

public class EfeitoMapper {
    
    public static EfeitoModel toModel(EfeitoDTO efeitoDTO) {
        EfeitoModel efeitoModel = new EfeitoModel();
        efeitoModel.setTipo(efeitoDTO.tipo());
        efeitoModel.setNome(efeitoDTO.nome());
        efeitoModel.setCausa(efeitoDTO.causa());
        return efeitoModel;
    }
}
