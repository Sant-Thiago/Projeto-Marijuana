package com.example.projetoplanta.Aroma.Mapper;

import com.example.projetoplanta.Aroma.DTO.AromaRequestDTO;
import com.example.projetoplanta.Aroma.DTO.AromaSelectedDTO;
import com.example.projetoplanta.Aroma.Module.AromaModel;

public class AromaMapper {
    
    public static AromaModel toModel(AromaRequestDTO aromaRequestDTO) {
        AromaModel aromaModel = new AromaModel();
        aromaModel.setTipo(aromaRequestDTO.tipo());
        aromaModel.setNome(aromaRequestDTO.nome());
        aromaModel.setCaracteristica(aromaRequestDTO.caracteristica());
        return aromaModel;
    }

    public static AromaSelectedDTO toDTO(AromaModel aromaModel) {
        AromaSelectedDTO aromaSelectedDTO = new AromaSelectedDTO(
            aromaModel.getId(),
            aromaModel.getTipo(),
            aromaModel.getNome(),
            aromaModel.getCaracteristica()
        );
        return aromaSelectedDTO;
    }
}
