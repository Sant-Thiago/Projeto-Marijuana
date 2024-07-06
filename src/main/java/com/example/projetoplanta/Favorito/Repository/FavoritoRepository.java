package com.example.projetoplanta.Favorito.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetoplanta.Favorito.Module.FavoritoModel;

@Repository
public interface FavoritoRepository extends JpaRepository<FavoritoModel, Integer> {

    List<FavoritoModel> findByFkUsuario(String fkUsuario);
    List<FavoritoModel> findByFkPlanta(String fkPlanta);
}
