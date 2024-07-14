package com.example.projetoplanta.Favorito.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetoplanta.Favorito.DTO.FavoritoRequestDTO;
import com.example.projetoplanta.Favorito.DTO.FavoritoResponseDTO;
import com.example.projetoplanta.Favorito.Mapper.FavoritoMapper;
import com.example.projetoplanta.Favorito.Module.FavoritoModel;
import com.example.projetoplanta.Favorito.Service.FavoritoService;
import com.example.projetoplanta.exceptions.NotFoundException;
import com.example.projetoplanta.exceptions.SystemException;

import jakarta.validation.Valid;


@RestController
public class FavoritoController {
    
    @Autowired
    FavoritoService favoritoService;
    
    @PostMapping("/favoritar/planta")
    public ResponseEntity<Object> favoritarPlanta(@RequestBody @Valid FavoritoRequestDTO favoritoRequestDTO) {
        ResponseEntity<Object> response;
        try {
            FavoritoModel favoritoSaved = favoritoService.save(favoritoRequestDTO);

            response = ResponseEntity.status(201).body(FavoritoMapper.toDTO(favoritoSaved));
        } catch (SystemException e) {
            response = ResponseEntity.status(400).body(e.getMensagem());
        }
        return response;
    }

    @DeleteMapping("/desfavoritar/planta/{id}")
    public ResponseEntity<Object> desfavoritarPlanta(@PathVariable(name = "id") Integer id) {
        ResponseEntity<Object> response;
        try {
            FavoritoModel favoritoDeleted = favoritoService.delete(id);
            
            response = ResponseEntity.status(200).body("Favorito:: '"+favoritoDeleted.getId() + "' deletado com sucesso.");
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMessage());
        } catch (SystemException e) {
            response = ResponseEntity.status(400).body(e.getMensagem());
        }
        return response;
    }

    @GetMapping("/listar/favorito/{id}") 
    public ResponseEntity<Object> listarFavorito(@PathVariable(value = "id") Integer id) {
        ResponseEntity<Object> response;
        try {
            FavoritoResponseDTO favoritoResponseDTO = favoritoService.selectById(id);
            response = ResponseEntity.status(200).body(favoritoResponseDTO);
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
        } catch (SystemException e) {
            response = ResponseEntity.status(400).body(e.getMensagem());
        }
        return response;
    }

    @GetMapping("/favoritos/usuario/{fkusuario}")
    public ResponseEntity<Object> listarUsuariosFavoritos(@PathVariable(name = "fkusuario") String fkUsuario) {
        ResponseEntity<Object> response;
        try {
            List<FavoritoResponseDTO> favoritoResponseDTOs = favoritoService.selectByFkUsuario(fkUsuario);

            response = ResponseEntity.status(200).body(favoritoResponseDTOs);    
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
        } catch (SystemException e) {
            response = ResponseEntity.status(400).body(e.getMensagem());
        }
        return response;
    }

    @GetMapping("/favoritos/planta/{fkplanta}")
    public ResponseEntity<Object> listarPlantasFavoritos(@PathVariable(name = "fkplanta") String fkPlanta) {
        ResponseEntity<Object> response;
        try {
            List<FavoritoResponseDTO> favoritoResponseDTOs = favoritoService.selectByFkPlanta(fkPlanta);

            response = ResponseEntity.status(200).body(favoritoResponseDTOs);    
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
        } catch (SystemException e) {
            response = ResponseEntity.status(400).body(e.getMensagem());
        }
        return response;
    }
    
    @GetMapping("/listar/favoritos")
    public ResponseEntity<Object> listarFavoritos() {
        ResponseEntity<Object> response;
        try {
            List<FavoritoResponseDTO> favoritoResponseDTOs = favoritoService.selectAll();
            
            response = ResponseEntity.status(200).body(favoritoResponseDTOs);
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
        } catch (SystemException e) {
            response = ResponseEntity.status(400).body(e.getMensagem());
        }
        return response;
    }

    private void methodsOn(FavoritoModel favorito) {
        favorito.add(linkTo(methodOn(FavoritoController.class).favoritarPlanta(null)).withRel("favoritarPlanta"));
        favorito.add(linkTo(methodOn(FavoritoController.class).desfavoritarPlanta(favorito.getId())).withRel("desfavoritarPlanta"));
        favorito.add(linkTo(methodOn(FavoritoController.class).listarUsuariosFavoritos(favorito.getFkUsuario().getId())).withRel("listarUsuariosFavoritos"));    
        favorito.add(linkTo(methodOn(FavoritoController.class).listarPlantasFavoritos(favorito.getFkPlanta().getId())).withRel("listarPlantasFavoritos"));    
        favorito.add(linkTo(methodOn(FavoritoController.class).listarFavoritos()).withRel("listarFavoritos"));    
    }
}
