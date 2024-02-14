package com.example.projetoplanta.com.example.projetoplanta.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetoplanta.com.example.projetoplanta.modules.SolicitacaoModel;
import com.example.projetoplanta.com.example.projetoplanta.repositories.SolicitacaoRepository;


@RestController
public class SolicitacaoController {
    
    @Autowired
    SolicitacaoRepository solicitacaoRepository;

    @PostMapping("/listar/solicitacoes")
    public ResponseEntity<List<SolicitacaoModel>> listarSolicitacoes() {
        List<SolicitacaoModel> listaSolicitacoes = solicitacaoRepository.findAll();
        // for (SolicitacaoModel solicitacao : listaSolicitacoes) {
        //     Integer id = solicitacao.getId();
        //     solicitacao.add(linkTo(methodOn(SolicitacaoController.class)).withRel("alguma coisa"));
        // }
        return ResponseEntity.status(HttpStatus.OK).body(listaSolicitacoes);
    }
}
