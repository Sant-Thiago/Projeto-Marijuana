package com.example.projetoplanta.Favorito.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projetoplanta.Favorito.DTO.FavoritoRequestDTO;
import com.example.projetoplanta.Favorito.DTO.FavoritoResponseDTO;
import com.example.projetoplanta.Favorito.Mapper.FavoritoMapper;
import com.example.projetoplanta.Favorito.Module.FavoritoModel;
import com.example.projetoplanta.Favorito.Repository.FavoritoRepository;
import com.example.projetoplanta.Planta.Module.PlantaModel;
import com.example.projetoplanta.Planta.Repository.PlantaRepository;
import com.example.projetoplanta.Usuario.Module.UsuarioModel;
import com.example.projetoplanta.Usuario.Repository.UsuarioRepository;
import com.example.projetoplanta.exceptions.NotFoundException;
import com.example.projetoplanta.exceptions.SystemException;


@Service
public class FavoritoService {

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PlantaRepository plantaRepository;

    public FavoritoModel save(FavoritoRequestDTO favoritoRequestDTO) {
        try {
            FavoritoModel favorito = new FavoritoModel();
            UsuarioModel usuario = usuarioRepository.findById(favoritoRequestDTO.fkUsuario()).get();
            PlantaModel planta = plantaRepository.findById(favoritoRequestDTO.fkPlanta()).get();

            favorito.setFkUsuario(usuario);
            favorito.setFkPlanta(planta);

            // Logger.saved("Planta "+favorito.getFkPlanta()+" favoritada com sucesso.");
            return favoritoRepository.save(favorito);
        } catch (Exception e) {
            // Logger.error("Erro ao definir favorito no sistema::\n\n" + e.getMessage());
            throw new SystemException();
        }
    }

    public FavoritoModel delete(Integer id) {
        try {
            Optional<FavoritoModel> optionalFavorito = favoritoRepository.findById(id);
            if (optionalFavorito.isEmpty()) {
                // Logger.notFound("Nenhuma favorito com o id:: "+id+" encontrado no sistema!");
                throw new NotFoundException().toFavorito(id);
            } 
            favoritoRepository.deleteById(id);
            // Logger.saved("Planta "+favorito.getFkPlanta()+" favoritada com sucesso.");
            return optionalFavorito.get();
        } catch (Exception e) {
            // Logger.error("Erro ao definir favorito no sistema::\n\n" + e.getMessage());
            throw new SystemException();
        }
    }

    public FavoritoResponseDTO selectById(Integer id) {
        Optional<FavoritoModel> optionalFavorito = favoritoRepository.findById(id);
        if (optionalFavorito.isEmpty()) {
            // Logger.notFound("Nenhuma favorito com o id:: "+id+" encontrado no sistema!");
            throw new NotFoundException().toFavorito(id);
        } 
        // Logger.saved("Planta "+favorito.getFkPlanta()+" favoritada com sucesso.");
        return FavoritoMapper.toDTO(optionalFavorito.get());
    }

    public List<FavoritoResponseDTO> selectByFkUsuario(String fkUsuario) {
        List<FavoritoModel> favoritos = favoritoRepository.findByFkUsuario(fkUsuario);
        if (favoritos.isEmpty()) {
            // Logger.notFound("Nenhuma favorito com o id:: "+id+" encontrado no sistema!");
            throw new NotFoundException().toFavorito();
        } 
        List<FavoritoResponseDTO> favoritoResponseDTOs = favoritos.stream().map(FavoritoMapper::toDTO).toList();
        // Logger.saved("Planta "+favorito.getFkPlanta()+" favoritada com sucesso.");
        return favoritoResponseDTOs;
    }

    public List<FavoritoResponseDTO> selectByFkPlanta(String fkPlanta) {
        List<FavoritoModel> favoritos = favoritoRepository.findByFkPlanta(fkPlanta);
        if (favoritos.isEmpty()) {
            // Logger.notFound("Nenhuma favorito com o id:: "+id+" encontrado no sistema!");
            throw new NotFoundException().toFavorito();
        } 
        // Logger.saved("Planta "+favorito.getFkPlanta()+" favoritada com sucesso.");
        return favoritos.stream().map(FavoritoMapper::toDTO).toList();
    }

    public List<FavoritoResponseDTO> selectAll() {
        List<FavoritoModel> favoritos = favoritoRepository.findAll();
        if (favoritos.isEmpty()) {
            // Logger.notFound("Nenhuma favorito com o id:: "+id+" encontrado no sistema!");
            throw new NotFoundException().toFavorito();
        } 
        List<FavoritoResponseDTO> favoritoResponseDTOs = new ArrayList<>();
        for (FavoritoModel favorito : favoritos) {
            favoritoResponseDTOs.add(FavoritoMapper.toDTO(favorito));
        }
        // Logger.saved("Planta "+favorito.getFkPlanta()+" favoritada com sucesso.");
        return favoritoResponseDTOs;
    }


}
