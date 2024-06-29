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

import com.example.projetoplanta.com.example.projetoplanta.exceptions.NotFoundException;
import com.example.projetoplanta.com.example.projetoplanta.modules.SolicitacaoModel;
import com.example.projetoplanta.com.example.projetoplanta.modules.UsuarioModel;
import com.example.projetoplanta.com.example.projetoplanta.repositories.SolicitacaoRepository;
import com.example.projetoplanta.com.example.projetoplanta.services.SolicitacaoService;


@RestController
public class SolicitacaoController {
    
    @Autowired
    SolicitacaoRepository solicitacaoRepository;

    SolicitacaoModel solicitacaoModel = new SolicitacaoModel();

    @GetMapping("/listar/solicitacoes")
    public ResponseEntity<Object> listarTodos() {
        ResponseEntity<Object> response;
        try {
            List<SolicitacaoModel> listaSolicitacoes = solicitacaoRepository.findAll();
            if (listaSolicitacoes.isEmpty()) {
                throw new NotFoundException("Nenhuma solicitação feita até o momento.");
            }
            for (SolicitacaoModel solicitacao : listaSolicitacoes) {
                methodsOn(solicitacao);
            }
            response = ResponseEntity.status(200).body(listaSolicitacoes);
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
        } catch (Exception e) {
            response = ResponseEntity.status(500).body("Erro ao listar todas solicitações.");
            throw new RuntimeException("Erro ao listar todas solicitações:: "+ e.getMessage());
        }
        return response;
    }

    @PostMapping("/solicitar/duende/{solicitante}")
    public ResponseEntity<Object> solicitarDuende(@PathVariable(name = "solicitante") UsuarioModel solicitante, @RequestParam("motivo") String motivo) {
        try {
            List<SolicitacaoModel> solicitacoes = solicitacaoRepository.findBySolicitanteAndTipo(solicitante, "DUENDE"); 
            String mensagem = solicitacaoService.solicitarDuende(solicitante, motivo);
            return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    private void methodsOn(SolicitacaoModel solicitacao) {
        usuario.add(linkTo(methodOn(UsuarioController.class).deletarUsuario(usuario.getId())).withRel("deletar"));
        solicitacaoModel.add(linkTo(methodOn(SolicitacaoController.class).solicitarDuende(null, null)).withRel("solicitarDuende"));
        usuario.add(linkTo(methodOn(UsuarioController.class).listarUsuario(usuario.getId())).withRel("listar"));
        usuario.add(linkTo(methodOn(UsuarioController.class).modificarUsuario(usuario.getId(), null)).withRel("modificarUsuário"));
        usuario.add(linkTo(methodOn(UsuarioController.class).statusUsuario(usuario.getId())).withRel("ativar"));
    }
}
