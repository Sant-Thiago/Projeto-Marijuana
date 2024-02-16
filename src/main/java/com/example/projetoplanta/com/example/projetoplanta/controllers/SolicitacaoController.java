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

import com.example.projetoplanta.com.example.projetoplanta.modules.SolicitacaoModel;
import com.example.projetoplanta.com.example.projetoplanta.modules.UsuarioModel;
import com.example.projetoplanta.com.example.projetoplanta.repositories.SolicitacaoRepository;


@RestController
public class SolicitacaoController {
    
    @Autowired
    SolicitacaoRepository solicitacaoRepository;

    SolicitacaoModel solicitacaoModel = new SolicitacaoModel();

    @GetMapping("/listar/solicitacoes")
    public ResponseEntity<List<SolicitacaoModel>> listarSolicitacoes() {
        List<SolicitacaoModel> listaSolicitacoes = solicitacaoRepository.findAll();
        if (!listaSolicitacoes.isEmpty()) {
            for (SolicitacaoModel solicitacaoModel : listaSolicitacoes) {
                solicitacaoModel.add(linkTo(methodOn(SolicitacaoController.class).solicitarDuende(null, null)).withRel("solicitarDuende"));
                solicitacaoModel.add(linkTo(methodOn(SolicitacaoController.class).solicitarFoto(null, null)).withRel("solicitarFoto"));
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaSolicitacoes);
    }

    @PostMapping("/solicitar/duende/{solicitante}")
    public ResponseEntity<SolicitacaoModel> solicitarDuende(@PathVariable(name = "solicitante") UsuarioModel solicitante, @RequestParam("motivo") String motivo) {
        solicitacaoModel.setSolicitante(solicitante);
        solicitacaoModel.setMotivo(motivo);
        solicitacaoModel.setTipo("DUENDE");
        solicitacaoModel.setStatus("PENDENTE");
        return ResponseEntity.status(HttpStatus.CREATED).body(solicitacaoRepository.save(solicitacaoModel));
    }

    @PostMapping("/solicitar/foto") // Método utilizado apenas pelo usuario
    public ResponseEntity<SolicitacaoModel> solicitarFoto(UsuarioModel usuarioModel, byte[] foto) {
        solicitacaoModel.setSolicitante(usuarioModel);
        solicitacaoModel.setFotoUsuario(foto);
        solicitacaoModel.setMotivo(null);
        solicitacaoModel.setTipo("FOTO");
        solicitacaoModel.setStatus("PENDENTE");
        return ResponseEntity.status(HttpStatus.CREATED).body(solicitacaoRepository.save(solicitacaoModel));
    }

}
