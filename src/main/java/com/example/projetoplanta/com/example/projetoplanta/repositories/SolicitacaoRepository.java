package com.example.projetoplanta.com.example.projetoplanta.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetoplanta.com.example.projetoplanta.modules.SolicitacaoModel;
import com.example.projetoplanta.com.example.projetoplanta.modules.UsuarioModel;

@Repository
public interface SolicitacaoRepository extends JpaRepository<SolicitacaoModel, Integer> {
    List<SolicitacaoModel> findBySolicitanteAndTipo(UsuarioModel solicitante, String tipo);
}
