package com.example.projetoplanta.Planta.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetoplanta.Planta.Module.PlantaModel;

@Repository
public interface PlantaRepository extends JpaRepository<PlantaModel, String> {}
