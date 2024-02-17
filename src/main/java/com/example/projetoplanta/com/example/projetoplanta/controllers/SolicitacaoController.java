package com.example.projetoplanta.com.example.projetoplanta.controllers;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetoplanta.com.example.projetoplanta.DTO.UsuarioRecordDTO;
import com.example.projetoplanta.com.example.projetoplanta.modules.SolicitacaoModel;
import com.example.projetoplanta.com.example.projetoplanta.services.SolicitacaoService;


@RestController
public class SolicitacaoController {
    
    @Autowired
    SolicitacaoService solicitacaoService;

    SolicitacaoModel solicitacaoModel = new SolicitacaoModel();

    @GetMapping("/listar/solicitacoes")
    public ResponseEntity<List<SolicitacaoModel>> listarSolicitacoes() {
        List<SolicitacaoModel> listaSolicitacoes = solicitacaoService.listarSolicitacoes();
        for (SolicitacaoModel solicitacaoModel : listaSolicitacoes) {
            solicitacaoModel.add(linkTo(methodOn(SolicitacaoController.class).solicitarDuende(null, null)).withRel("solicitarDuende"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaSolicitacoes);
    }

    @PostMapping("/solicitar/duende/{solicitante}")
    public ResponseEntity<Object> solicitarDuende(@PathVariable(name = "solicitante") UsuarioRecordDTO solicitante, @RequestParam("motivo") String motivo) {
        try {
            solicitacaoService.solicitarDuende(solicitante, motivo);
            return ResponseEntity.status(HttpStatus.CREATED).body("Solicitação criada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao enviar a solicitação.");
        }
    }
}
