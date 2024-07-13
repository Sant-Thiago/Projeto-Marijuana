package com.example.projetoplanta.Efeito.Mapper;

import com.example.projetoplanta.Efeito.DTO.EfeitoDTO;
import com.example.projetoplanta.Efeito.Module.EfeitoModel;

public class EfeitoMapper {
    
    public static EfeitoModel toModel(EfeitoDTO efeitoDTO) {
        EfeitoModel aromaModel = new EfeitoModel();
        aromaModel.setTipo(efeitoDTO.tipo());
        aromaModel.setNome(efeitoDTO.nome());
        aromaModel.setCausa(efeitoDTO.causa());
        return aromaModel;
    }
}
