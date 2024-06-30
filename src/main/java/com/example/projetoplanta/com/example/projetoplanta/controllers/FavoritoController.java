package com.example.projetoplanta.com.example.projetoplanta.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetoplanta.com.example.projetoplanta.DTO.FavoritoRecordDTO;
import com.example.projetoplanta.com.example.projetoplanta.exceptions.NotFoundException;
import com.example.projetoplanta.com.example.projetoplanta.modules.FavoritoModel;
import com.example.projetoplanta.com.example.projetoplanta.modules.PlantaModel;
import com.example.projetoplanta.com.example.projetoplanta.modules.UsuarioModel;
import com.example.projetoplanta.com.example.projetoplanta.modules.PrimaryKey.FavoritoId;
import com.example.projetoplanta.com.example.projetoplanta.repositories.FavoritoRepository;
import com.example.projetoplanta.com.example.projetoplanta.services.FavoritoService;

import jakarta.validation.Valid;


@RestController
public class FavoritoController {
    
    @Autowired
    FavoritoRepository favoritoRepository;

    @PostMapping("/favoritar/planta")
    public ResponseEntity<Object> favoritarPlanta(@RequestBody @Valid FavoritoRecordDTO favoritoDto) {
        ResponseEntity<Object> response;
        try {
            FavoritoModel favorito = new FavoritoModel();
            BeanUtils.copyProperties(favoritoDto, favorito);
            favoritoRepository.save(favorito);
            response = ResponseEntity.status(201).body("Planta "+favorito.getFkPlanta()+" favoritada com sucesso.");
        } catch (Exception e) {
            response = ResponseEntity.status(400).body(e.getMessage());
            throw new RuntimeException("Erro ao favoritar a planta:: "+ e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/desfavoritar/planta/{fkPlanta}{fkUsuario}")
    public ResponseEntity<Object> desfavoritarPlanta(@PathVariable(name = "fkPlanta") String fkPlanta, @PathVariable(value = "fkUsuario") String fkUsuario) {
        ResponseEntity<Object> response;
        try {
            FavoritoId favoritoId = new FavoritoId(fkPlanta, fkUsuario);
            Optional<FavoritoModel> optionalFavorito = favoritoRepository.findById(favoritoId);
            if (optionalFavorito.isEmpty()) {
                throw new NotFoundException().toFavorito(fkPlanta, fkUsuario);
            }
            favoritoRepository.deleteById(favoritoId);
            response = ResponseEntity.status(201).body("Planta com o id:: "+fkPlanta+" do Usuário com o id:: "+fkUsuario+" deletado com sucesso.");
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
        } catch (Exception e) {
            response = ResponseEntity.status(400).body(e.getMessage());
            throw new RuntimeException("Erro ao deletar a Planta com o id:: "+fkPlanta+" do Usuário com o id:: "+fkUsuario+" da lista de Favoritos:: "+e.getMessage());
        }
        return response;
    }

    @GetMapping("/listar/favoritos/{fkusuario}")
    public ResponseEntity<Object> listarUsuariosFavoritos(@PathVariable(name = "fkusuario") UsuarioModel fkUsuario) {
        try {
            List<FavoritoModel> listaFavoritos = favoritoService.listarUsuariosFavoritos(fkUsuario);
            for (FavoritoModel favorito : listaFavoritos) {
                favorito.add(linkTo(methodOn(FavoritoController.class).des_favoritarPlanta(fkUsuario, null)).withRel("favoritarPlanta"));
                favorito.add(linkTo(methodOn(FavoritoController.class).listarFavoritos()).withRel("listarFavoritos"));
            }
            return ResponseEntity.status(HttpStatus.OK).body(listaFavoritos);    
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMensagem());
        }
    }

    @GetMapping("/plantas/favoritas/{fkplanta}")
    public ResponseEntity<Object> listarPlantasFavoritos(@PathVariable(name = "fkplanta") PlantaModel fkPlanta) {
        try {
            var listaFavoritos = favoritoService.listarPlantasFavoritas(fkPlanta);
            return ResponseEntity.status(HttpStatus.OK).body(listaFavoritos);    
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMensagem());
        }
    }
    
    @GetMapping("/listar/favoritos")
    public ResponseEntity<Object> listarFavoritos() {
        try {
            List<FavoritoModel> listaFavoritos = favoritoService.listarFavoritos();
            for (FavoritoModel favorito : listaFavoritos) {
                favorito.add(linkTo(methodOn(FavoritoController.class).des_favoritarPlanta(null, null)).withRel("favoritarPlanta"));
                favorito.add(linkTo(methodOn(FavoritoController.class).listarUsuariosFavoritos(null)).withRel("listarUsuariosFavoritos"));
            }
            return ResponseEntity.status(HttpStatus.OK).body(listaFavoritos);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMensagem());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}
