package com.example.projetoplanta.Efeito.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetoplanta.Efeito.Module.EfeitoModel;

@Repository
public interface EfeitoRepository extends JpaRepository<EfeitoModel, Integer> {
    
}
