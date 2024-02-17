package com.example.projetoplanta.com.example.projetoplanta.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetoplanta.com.example.projetoplanta.modules.FavoritoModel;
import com.example.projetoplanta.com.example.projetoplanta.modules.PlantaModel;
import com.example.projetoplanta.com.example.projetoplanta.modules.UsuarioModel;
import com.example.projetoplanta.com.example.projetoplanta.services.FavoritoService;


@RestController
public class FavoritoController {
    
    @Autowired
    FavoritoService favoritoService;

    @PostMapping("/favoritar/planta/{fkusuario}")
    public ResponseEntity<Object> favoritarPlanta(@PathVariable(name = "fkusuario") UsuarioModel fkusuario, @RequestParam("fkPlanta") PlantaModel fkplanta) {
        try {
            favoritoService.favoritarPlanta(fkusuario, fkplanta);
            return ResponseEntity.status(HttpStatus.CREATED).body("Planta favoritada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao favoritar planta.");
        }
    }

    @GetMapping("/listar/favoritos/{fkusuario}")
    public ResponseEntity<List<FavoritoModel>> listarFavoritos(@PathVariable(name = "fkusuario") UsuarioModel fkUsuario) {
        List<FavoritoModel> listaFavoritos = favoritoService.listarFavoritos(fkUsuario);
        for (FavoritoModel favorito : listaFavoritos) {
            favorito.add(linkTo(methodOn(FavoritoController.class).favoritarPlanta(fkUsuario, null)).withRel("favoritarPlanta"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaFavoritos);
    }
}
