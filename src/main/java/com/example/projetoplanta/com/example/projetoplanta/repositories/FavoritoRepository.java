package com.example.projetoplanta.com.example.projetoplanta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetoplanta.com.example.projetoplanta.modules.FavoritoModel;
import com.example.projetoplanta.com.example.projetoplanta.modules.PrimaryKey.FavoritoPK;

@Repository
public interface FavoritoRepository extends JpaRepository<FavoritoModel, FavoritoPK> {
}
