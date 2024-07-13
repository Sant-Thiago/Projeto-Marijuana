package com.example.projetoplanta.Favorito.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.projetoplanta.Favorito.DTO.FavoritoDTO;
import com.example.projetoplanta.Favorito.DTO.FavoritoRequestDTO;
import com.example.projetoplanta.Favorito.Module.FavoritoModel;
import com.example.projetoplanta.Planta.Repository.PlantaRepository;
import com.example.projetoplanta.Usuario.Repository.UsuarioRepository;

@Component
public class FavoritoMapper {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PlantaRepository plantaRepository;
    
    

    public FavoritoModel toModel(FavoritoRequestDTO favoritoRequestDTO) {
        FavoritoModel favoritoModel = new FavoritoModel();
        favoritoModel.setFkUsuario(usuarioRepository.findById(favoritoRequestDTO.fkUsuario()).get());
        favoritoModel.setFkPlanta(plantaRepository.findById(favoritoRequestDTO.fkPlanta()).get());
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
