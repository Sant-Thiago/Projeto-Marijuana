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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetoplanta.com.example.projetoplanta.modules.FavoritoModel;
import com.example.projetoplanta.com.example.projetoplanta.modules.PlantaModel;
import com.example.projetoplanta.com.example.projetoplanta.modules.UsuarioModel;
import com.example.projetoplanta.com.example.projetoplanta.repositories.FavoritoRepository;

import jakarta.validation.Valid;

@RestController
public class FavoritoController {
    
    @Autowired
    FavoritoRepository favoritoRepository;

    @PostMapping("/favoritar/planta/{fkusuario}")
    public ResponseEntity<FavoritoModel> favoritarPlanta(@PathVariable(name = "fkusuario") UsuarioModel fkusuario, @RequestParam("fkPlanta") PlantaModel fkplanta) {
        var favorito = new FavoritoModel();
        favorito.setFkPlanta(fkplanta);
        favorito.setFkUsuario(fkusuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(favoritoRepository.save(favorito));
    }

    @GetMapping("/listar/favoritos/{fkusuario}")
    public ResponseEntity<FavoritoModel> listarFavoritos(@RequestBody @Valid ) {
        var listaFavoritos = favoritoRepository.findById(fkusuario);
        if (!listaFavoritos.isEmpty()) {
            for (FavoritoModel favorito : listaFavoritos) {
                favorito.add(linkTo(methodOn(FavoritoController.class).favoritarPlanta(null, null)).withRel("favoritarPlanta"));
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaFavoritos);
    }
}
