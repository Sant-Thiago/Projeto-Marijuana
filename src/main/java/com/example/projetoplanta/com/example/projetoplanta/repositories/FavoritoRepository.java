package com.example.projetoplanta.com.example.projetoplanta.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetoplanta.com.example.projetoplanta.modules.FavoritoModel;
import com.example.projetoplanta.com.example.projetoplanta.modules.PlantaModel;
import com.example.projetoplanta.com.example.projetoplanta.modules.UsuarioModel;
import com.example.projetoplanta.com.example.projetoplanta.modules.PrimaryKey.FavoritoId;

@Repository
public interface FavoritoRepository extends JpaRepository<FavoritoModel, FavoritoId> {
    List<FavoritoModel> findByFkUsuario(UsuarioModel fkUsuario);
    List<FavoritoModel> findByFkPlanta(PlantaModel fkPlanta);
}
