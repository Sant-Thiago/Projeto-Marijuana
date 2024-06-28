package com.example.projetoplanta.com.example.projetoplanta.services;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projetoplanta.com.example.projetoplanta.exceptions.NotFoundException;
import com.example.projetoplanta.com.example.projetoplanta.modules.FavoritoModel;
import com.example.projetoplanta.com.example.projetoplanta.modules.PlantaModel;
import com.example.projetoplanta.com.example.projetoplanta.modules.UsuarioModel;
import com.example.projetoplanta.com.example.projetoplanta.modules.PrimaryKey.FavoritoPK;
import com.example.projetoplanta.com.example.projetoplanta.repositories.FavoritoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class FavoritoService {

    @Autowired
    FavoritoRepository favoritoRepository;

    @PersistenceContext
    private EntityManager entityManager;


    public List<FavoritoModel> listarFavoritos() {
        List<FavoritoModel> listaFavoritos = favoritoRepository.findAll();
        if (listaFavoritos.isEmpty()) {
            throw new NotFoundException("Planta não tem favoritos.");
        }
        return listaFavoritos;
    }

    // MUDAR A QUERY PARA RETORNAR AS PLANTAS COM MAIS FAVORITOS
    public Optional<FavoritoModel> listarPlantasFavoritas(PlantaModel plantaModel) {
        var favoritoPK = new FavoritoPK();
        favoritoPK.setPlanta(plantaModel.getId());
        // .getResultList();
            throw new NotFoundException("Planta não tem favoritos.");
    }

    public List listarUsuariosFavoritos(UsuarioModel fkUsuario) {
        List listaFavoritos = entityManager.createQuery("SELECT fkUsuario, fkPlanta, data FROM FavoritoModel WHERE fkUsuario = :fkusuario")
        .setParameter("fkusuario", fkUsuario)
        .getResultList();
        if (listaFavoritos.isEmpty()) {
            throw new NotFoundException("Nenhuma lista ligada a esse usuário.");
        }
        return listaFavoritos;
    }

    public String des_favoritarPlanta(UsuarioModel fkUsuario, PlantaModel fkPlanta) {
        var favoritoPK = new FavoritoPK();
        favoritoPK.setPlanta(fkPlanta.getId());
        favoritoPK.setUsuario(fkUsuario.getId());
        var favorito = new FavoritoModel();
        favorito.setId(favoritoPK);
        favorito.setFkPlanta(fkPlanta);
        favorito.setFkUsuario(fkUsuario);
        try {
            List selectFavorito = entityManager.createQuery("SELECT fkUsuario, fkPlanta FROM FavoritoModel WHERE fkUsuario = :fkusuario AND fkPlanta = :fkplanta")
            .setParameter("fkusuario", fkUsuario)
            .setParameter("fkplanta", fkPlanta)
            .getResultList();
            if (selectFavorito.size() == 0) {
                try {
                    favoritoRepository.save(favorito);
                    return "FAVORITADA";
                } catch (Exception e) {
                    throw new RuntimeException("Erro ao favoritar a planta: "+ e.getMessage());
                }
            } else {
                try {
                    favoritoRepository.delete(favorito);
                    return "DESFAVORITADA";
                } catch (Exception e) {
                    throw new RuntimeException("Erro ao desfavoritar a planta: "+ e.getMessage());
                } 
            }
        } catch (Exception e) {
            throw new NotFoundException("Nenhum favorito encontrado do usuario: "+fkUsuario+" com a planta: "+fkPlanta+" \nERRO: "+e);
        }
    }
}
