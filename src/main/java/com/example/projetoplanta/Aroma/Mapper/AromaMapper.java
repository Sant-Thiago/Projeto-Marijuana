package com.example.projetoplanta.Aroma.Mapper;

import com.example.projetoplanta.Aroma.DTO.AromaDTO;
import com.example.projetoplanta.Aroma.Module.AromaModel;

public class AromaMapper {
    
    public static AromaModel toModel(AromaDTO aromaDTO) {
        AromaModel aromaModel = new AromaModel();
        aromaModel.setTipo(aromaDTO.tipo());
        aromaModel.setNome(aromaDTO.nome());
        aromaModel.setCaracteristica(aromaDTO.caracteristica());
        return aromaModel;
    }
}
