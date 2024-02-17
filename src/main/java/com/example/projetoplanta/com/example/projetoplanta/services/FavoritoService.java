package com.example.projetoplanta.com.example.projetoplanta.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projetoplanta.com.example.projetoplanta.modules.FavoritoModel;
import com.example.projetoplanta.com.example.projetoplanta.modules.PlantaModel;
import com.example.projetoplanta.com.example.projetoplanta.modules.UsuarioModel;
import com.example.projetoplanta.com.example.projetoplanta.repositories.FavoritoRepository;
import com.example.projetoplanta.com.example.projetoplanta.services.exceptions.DadoNaoEncontradoException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class FavoritoService {

    @Autowired
    FavoritoRepository favoritoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List listarFavoritos(UsuarioModel fkUsuario) {
        List listaFavoritos = entityManager.createQuery("SELECT * FROM favorito WHERE fkUsuario = :fkusuario")
        .setParameter("fkusuario", fkUsuario)
        .getResultList();
        if (listaFavoritos.isEmpty()) {
            throw new DadoNaoEncontradoException("Nenhuma lista ligada a esse usuário.");
        }
        return listaFavoritos;
    }

    public void favoritarPlanta(UsuarioModel fkUsuario, PlantaModel fkPlanta) {
        try {
            Integer id = (Integer) entityManager.createQuery("SELECT id FROM favorito WHERE fkUsuario = :fkusuario AND fkPlanta = :fkplanta")
            .setParameter("fkusuario", fkUsuario)
            .setParameter("fkplanta", fkPlanta)
            .getSingleResult();
            if (id > 0) { 
                var favorito = new FavoritoModel();
                favorito.setFkPlanta(fkPlanta);
                favorito.setFkUsuario(fkUsuario);
                try {
                    favoritoRepository.save(favorito);
                } catch (Exception e) {
                    throw new RuntimeException("Erro ao favoritar a planta: "+ e.getMessage());
                }
            } 
        } catch (Exception e) {
            throw new RuntimeException("Erro com a query: "+ e.getMessage());
            
        }
    }

   public void apagarPlantaFavorita(UsuarioModel fkUsuario, PlantaModel fkPlanta) {
       try {
            Integer id = (Integer) entityManager.createQuery("SELECT id FROM favorito WHERE fkUsuario = :fkusuario AND fkPlanta = :fkplanta")
            .setParameter("fkusuario", fkUsuario)
            .setParameter("fkplanta", fkPlanta)
            .getSingleResult();
            if (id > 0) favoritoRepository.deleteById(id);
            else if (id < 0) throw new DadoNaoEncontradoException("Planta: "+fkPlanta+" não favoritada pelo usuário: "+fkUsuario);
        } catch (Exception e) {
            throw new RuntimeException("Ero ao deletar o favorito com o id: "+e.getMessage());
        }
   }
}
