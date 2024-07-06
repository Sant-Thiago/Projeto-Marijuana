package com.example.projetoplanta.Solicitacao.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetoplanta.Solicitacao.Module.SolicitacaoModel;
import com.example.projetoplanta.Usuario.Module.UsuarioModel;

@Repository
public interface SolicitacaoRepository extends JpaRepository<SolicitacaoModel, Integer> {
    List<SolicitacaoModel> findBySolicitanteAndTipo(UsuarioModel solicitante, String tipo);
}
