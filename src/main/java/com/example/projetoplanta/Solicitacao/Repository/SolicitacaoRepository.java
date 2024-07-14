package com.example.projetoplanta.Solicitacao.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.projetoplanta.Solicitacao.Module.SolicitacaoModel;

@Repository
public interface SolicitacaoRepository extends JpaRepository<SolicitacaoModel, Integer> {
    @Query("SELECT s FROM SolicitacaoModel s WHERE s.solicitante.Id = :solicitante AND s.tipo = :tipo")
    List<SolicitacaoModel> findBySolicitanteAndTipo(@Param("solicitante") String solicitante, @Param("tipo") String tipo);
}
