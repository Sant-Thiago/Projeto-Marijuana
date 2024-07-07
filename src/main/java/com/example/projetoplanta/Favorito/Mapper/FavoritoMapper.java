package com.example.projetoplanta.Favorito.Mapper;

import com.example.projetoplanta.Favorito.DTO.FavoritoDTO;
import com.example.projetoplanta.Favorito.DTO.FavoritoRequestDTO;
import com.example.projetoplanta.Favorito.Module.FavoritoModel;

public class FavoritoMapper {
    
    public static FavoritoModel toModel(FavoritoRequestDTO favoritoRequestDTO) {
        FavoritoModel favoritoModel = new FavoritoModel();
        favoritoModel.setFkUsuario(favoritoRequestDTO.fkUsuario());
        favoritoModel.setFkPlanta(favoritoRequestDTO.fkPlanta());
        return favoritoModel;
    }
    
    public static FavoritoDTO toDTO(FavoritoModel favoritoModel) {
        FavoritoDTO favoritoDTO = new FavoritoDTO(
            favoritoModel.getId(),
            favoritoModel.getFkUsuario(),
            favoritoModel.getFkPlanta(),
            favoritoModel.getDataRegistro()
        );
        return favoritoDTO;
    }
}
