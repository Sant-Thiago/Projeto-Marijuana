package com.example.projetoplanta.Favorito.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.projetoplanta.Favorito.Module.FavoritoModel;

@Repository
public interface FavoritoRepository extends JpaRepository<FavoritoModel, Integer> {
    @Query("SELECT f FROM favorito FavoritoModel WHERE f.fkUsuario.id = :fkUsuario")
    List<FavoritoModel> findByFkUsuario(@Param("fkUsuario")String fkUsuario);

    @Query("SELECT f FROM favorito FavoritoModel WHERE f.fkPlanta.id = :fkPlanta")
    List<FavoritoModel> findByFkPlanta(@Param("fkPlanta") String fkPlanta);
}
