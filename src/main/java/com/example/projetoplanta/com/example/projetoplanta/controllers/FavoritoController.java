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
import com.example.projetoplanta.com.example.projetoplanta.services.exceptions.DadoNaoEncontradoException;


@RestController
public class FavoritoController {
    
    @Autowired
    FavoritoService favoritoService;

    @PostMapping("/des_favoritar/planta")
    public ResponseEntity<Object> des_favoritarPlanta(@RequestParam("fkUsuario") UsuarioModel fkusuario, @RequestParam("fkPlanta") PlantaModel fkplanta) {
        try {
            String mensagem = favoritoService.des_favoritarPlanta(fkusuario, fkplanta);
            return ResponseEntity.status(HttpStatus.CREATED).body("Planta "+mensagem+" com sucesso.");
        } catch (DadoNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMensagem());
        }
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
        } catch (DadoNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMensagem());
        }
    }

    @GetMapping("/plantas/favoritas/{fkplanta}")
    public ResponseEntity<Object> listarPlantasFavoritos(@PathVariable(name = "fkplanta") PlantaModel fkPlanta) {
        try {
            var listaFavoritos = favoritoService.listarPlantasFavoritas(fkPlanta);
            return ResponseEntity.status(HttpStatus.OK).body(listaFavoritos);    
        } catch (DadoNaoEncontradoException e) {
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
        } catch (DadoNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMensagem());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}
