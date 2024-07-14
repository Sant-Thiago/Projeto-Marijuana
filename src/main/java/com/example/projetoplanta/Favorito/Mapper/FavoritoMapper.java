package com.example.projetoplanta.Favorito.Mapper;

import org.springframework.stereotype.Component;

import com.example.projetoplanta.Favorito.DTO.FavoritoResponseDTO;
import com.example.projetoplanta.Favorito.Module.FavoritoModel;

@Component
public class FavoritoMapper {
    
    public static FavoritoResponseDTO toDTO(FavoritoModel favoritoModel) {
        FavoritoResponseDTO favoritoDTO = new FavoritoResponseDTO(
            favoritoModel.getId(),
            favoritoModel.getFkUsuario(),
            favoritoModel.getFkPlanta(),
            favoritoModel.getDataRegistro()
        );
        return favoritoDTO;
    }
}
