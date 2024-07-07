package com.example.projetoplanta.Favorito.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetoplanta.Favorito.DTO.FavoritoDTO;
import com.example.projetoplanta.Favorito.DTO.FavoritoRequestDTO;
import com.example.projetoplanta.Favorito.Mapper.FavoritoMapper;
import com.example.projetoplanta.Favorito.Module.FavoritoModel;
import com.example.projetoplanta.Favorito.Repository.FavoritoRepository;
import com.example.projetoplanta.exceptions.NotFoundException;

import jakarta.validation.Valid;


@RestController
public class FavoritoController {
    
    @Autowired
    FavoritoRepository favoritoRepository;

    @PostMapping("/favoritar/planta")
    public ResponseEntity<Object> favoritarPlanta(@RequestBody @Valid FavoritoRequestDTO favoritoRequestDTO) {
        ResponseEntity<Object> response;
        try {
            FavoritoModel favorito = FavoritoMapper.toModel(favoritoRequestDTO);

            favorito = favoritoRepository.save(favorito);
            response = ResponseEntity.status(201).body(FavoritoMapper.toDTO(favorito));
            // Logger.saved("Planta "+favorito.getFkPlanta()+" favoritada com sucesso.");
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao favoritar a planta!");
            // Logger.error("Erro ao favoritar a planta:: "+ favoritoDto + " do sistema!\n\n"e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/desfavoritar/planta/{id}")
    public ResponseEntity<Object> desfavoritarPlanta(@PathVariable(name = "id") Integer id) {
        ResponseEntity<Object> response;
        try {
            Optional<FavoritoModel> optionalFavorito = favoritoRepository.findById(id);
            if (optionalFavorito.isEmpty()) {
                throw new NotFoundException().toFavorito(id);
            }
            favoritoRepository.deleteById(id);
            response = ResponseEntity.status(200).body("Favorito com o id:: "+id+" deletado com sucesso.");
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
            // Logger.notFound("Nenhuma favorito com o id:: "+id+" encontrado no sistema!");
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao deletar o Favorito com o id:: "+id+" do sistema!");
            // Logger.error("Erro ao deletar o Favorito com o id:: "+id+" do sistema!\n\n"e.getMessage());
        }
        return response;
    }

    @GetMapping("/listar/favoritos/{fkusuario}")
    public ResponseEntity<Object> listarUsuariosFavoritos(@PathVariable(name = "fkusuario") String fkUsuario) {
        ResponseEntity<Object> response;
        try {
            List<FavoritoModel> listaFavoritos = favoritoRepository.findByFkUsuario(fkUsuario);
            if (listaFavoritos.isEmpty()) {
                throw new NotFoundException().toFavorito();
            }
            List<FavoritoDTO> favoritoDTOs = new ArrayList<>();
            for (FavoritoModel favorito : listaFavoritos) {
                methodsOn(favorito);
                favoritoDTOs.add(FavoritoMapper.toDTO(favorito));
            }
            response = ResponseEntity.status(200).body(favoritoDTOs);    
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao listar os favoritos do Usuário:: "+ fkUsuario);
            // Logger.error("Erro ao listar os favoritos do Usuário:: "+ fkUsuario+" do sistema!\n\n"e.getMessage());
        }
        return response;
    }

    @GetMapping("/plantas/favoritas/{fkplanta}")
    public ResponseEntity<Object> listarPlantasFavoritos(@PathVariable(name = "fkplanta") String fkPlanta) {
        ResponseEntity<Object> response;
        try {
            List<FavoritoModel> listaFavoritos = favoritoRepository.findByFkPlanta(fkPlanta);
            if (listaFavoritos.isEmpty()) {
                throw new NotFoundException().toFavorito();
            }
            List<FavoritoDTO> favoritoDTOs = new ArrayList<>();
            for (FavoritoModel favorito : listaFavoritos) {
                methodsOn(favorito);
                favoritoDTOs.add(FavoritoMapper.toDTO(favorito));
            }
            response = ResponseEntity.status(200).body(favoritoDTOs);    
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao listar os favoritos da planta:: "+ fkPlanta);
            // Logger.error("Erro ao listar os favoritos da planta:: "+ fkPlanta+" do sistema!\n\n"e.getMessage());
        }
        return response;
    }
    
    @GetMapping("/listar/favoritos")
    public ResponseEntity<Object> listarFavoritos() {
        ResponseEntity<Object> response;
        try {
            List<FavoritoModel> listaFavoritos = favoritoRepository.findAll();
            if (listaFavoritos.isEmpty()) {
                throw new NotFoundException().toFavorito();
            }
            List<FavoritoDTO> favoritoDTOs = new ArrayList<>();
            for (FavoritoModel favorito : listaFavoritos) {
                methodsOn(favorito);
                favoritoDTOs.add(FavoritoMapper.toDTO(favorito));
            }
            response = ResponseEntity.status(200).body(favoritoDTOs);
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao listar todos os dados dos favoritos!");
            // Logger.error("Erro ao listar todos os dados dos favoritos do sistema!\n\n"e.getMessage());
        }
        return response;
    }

    private void methodsOn(FavoritoModel favorito) {
        favorito.add(linkTo(methodOn(FavoritoController.class).favoritarPlanta(null)).withRel("favoritarPlanta"));
        favorito.add(linkTo(methodOn(FavoritoController.class).desfavoritarPlanta(null)).withRel("desfavoritarPlanta"));
        favorito.add(linkTo(methodOn(FavoritoController.class).listarUsuariosFavoritos(null)).withRel("listarUsuariosFavoritos"));    
        favorito.add(linkTo(methodOn(FavoritoController.class).listarPlantasFavoritos(null)).withRel("listarPlantasFavoritos"));    
        favorito.add(linkTo(methodOn(FavoritoController.class).listarFavoritos()).withRel("listarFavoritos"));    
    }
}
