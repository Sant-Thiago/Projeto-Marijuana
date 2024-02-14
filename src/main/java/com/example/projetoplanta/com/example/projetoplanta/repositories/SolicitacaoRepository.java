package com.example.projetoplanta.com.example.projetoplanta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetoplanta.com.example.projetoplanta.modules.SolicitacaoModel;

@Repository
public interface SolicitacaoRepository extends JpaRepository<SolicitacaoModel, Integer> {
    
}
