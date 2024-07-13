package com.example.projetoplanta.Aroma.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetoplanta.Aroma.Module.AromaModel;

@Repository
public interface AromaRepository extends JpaRepository<AromaModel, Integer> {
    
}
