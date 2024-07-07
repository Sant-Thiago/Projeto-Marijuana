package com.example.projetoplanta.Favorito.Mapper;

import com.example.projetoplanta.Favorito.DTO.FavoritoDTO;
import com.example.projetoplanta.Favorito.DTO.FavoritoRequestDTO;
import com.example.projetoplanta.Favorito.Module.FavoritoModel;
import com.example.projetoplanta.Planta.Module.PlantaModel;
import com.example.projetoplanta.Planta.Repository.PlantaRepository;
import com.example.projetoplanta.Usuario.Module.UsuarioModel;
import com.example.projetoplanta.Usuario.Repository.UsuarioRepository;

public class FavoritoMapper {
    static UsuarioRepository usuarioRepository;
    static PlantaRepository plantaRepository;
    
    public static FavoritoModel toModel(FavoritoRequestDTO favoritoRequestDTO) {
        FavoritoModel favoritoModel = new FavoritoModel();
        UsuarioModel fkUsuario = usuarioRepository.findById(favoritoRequestDTO.fkUsuario()).get();
        PlantaModel fkPlanta = plantaRepository.findById(favoritoRequestDTO.fkPlanta()).get();
        favoritoModel.setFkUsuario(fkUsuario);
        favoritoModel.setFkPlanta(fkPlanta);
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
