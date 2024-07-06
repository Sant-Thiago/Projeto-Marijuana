package com.example.projetoplanta.Solicitacao.Controller;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetoplanta.Solicitacao.Module.SolicitacaoModel;
import com.example.projetoplanta.Solicitacao.Repository.SolicitacaoRepository;
import com.example.projetoplanta.Solicitacao.enums.tipoSolicitacao;
import com.example.projetoplanta.Usuario.Module.UsuarioModel;
import com.example.projetoplanta.exceptions.NotFoundException;


@RestController
public class SolicitacaoController {
    
    @Autowired
    SolicitacaoRepository solicitacaoRepository;

    @GetMapping("/listar/solicitacoes")
    public ResponseEntity<Object> listarTodos() {
        ResponseEntity<Object> response;
        try {
            List<SolicitacaoModel> listaSolicitacoes = solicitacaoRepository.findAll();
            if (listaSolicitacoes.isEmpty()) {
                throw new NotFoundException().toSolicitacao();
            }
            for (SolicitacaoModel solicitacao : listaSolicitacoes) {
                methodsOn(solicitacao);
            }
            response = ResponseEntity.status(200).body(listaSolicitacoes);
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao listar todas solicitações.");
            throw new RuntimeException("Erro ao listar todas solicitações:: "+ e.getMessage());
        }
        return response;
    }

    @PostMapping("/solicitar/duende/{solicitante}")
    public ResponseEntity<Object> solicitarDuende(@PathVariable(name = "solicitante") UsuarioModel solicitante, @RequestParam("motivo") String motivo) {
        ResponseEntity<Object> response;
        try {
            List<SolicitacaoModel> solicitacoes = solicitacaoRepository.findBySolicitanteAndTipo(solicitante, tipoSolicitacao.DUENDE.getValor());
            if (solicitacoes.size() > 3) {
                response = ResponseEntity.status(200).body("A solicitação está em análise.");
            } else {
                SolicitacaoModel solicitacao = new SolicitacaoModel();
                solicitacao.setSolicitante(solicitante);
                solicitacao.setMotivo(motivo);
                solicitacao.setTipo(tipoSolicitacao.DUENDE.getValor());
                solicitacao.setStatus("PENDENTE");
                
                solicitacaoRepository.save(solicitacao);
                response = ResponseEntity.status(201).body("Solicitação feita com sucesso!");
            }
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao solicitar modificação para Duende!");
            throw new RuntimeException("Erro ao solicitar modificação para Duende:: "+ e.getMessage());
        }
        return response;
    }

    @PostMapping("/solicitar/foto/{solicitante}")
    public ResponseEntity<Object> solicitarFoto(@PathVariable(name = "solicitante") UsuarioModel solicitante, @RequestParam("foto") String foto) {
        ResponseEntity<Object> response;
        try {
            SolicitacaoModel solicitacao = new SolicitacaoModel();
            solicitacao.setSolicitante(solicitante);
            solicitacao.setMotivo(null);
            solicitacao.setTipo(tipoSolicitacao.FOTO.getValor());
            solicitacao.setFotoUsuario(foto);
            solicitacao.setStatus("PENDENTE");

            solicitacaoRepository.save(solicitacao);
            response = ResponseEntity.status(201).body("Solicitação da foto feita com sucesso!");
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao solicitar modificação de Foto!");
            throw new RuntimeException("Erro ao solicitar modificação de Foto:: "+ e.getMessage());
        }
        return response;
    }

    @GetMapping("/listar/solicitacao/{id}")
    public ResponseEntity<Object> listar(@PathVariable(value = "id") Integer id) {
        ResponseEntity<Object> response;
        try {
            Optional<SolicitacaoModel> optionalSolicitacao = solicitacaoRepository.findById(id);
            if (optionalSolicitacao.isEmpty()) {
                throw new NotFoundException().toSolicitacao(id);
            }
            SolicitacaoModel solicitacao = optionalSolicitacao.get();
            methodsOn(solicitacao);
            response = ResponseEntity.status(200).body(solicitacao);
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao selecionar a solicitação com o id:: "+id);
            throw new RuntimeException("Erro ao selecionar a solicitação com o id:: "+id+":: "+ e.getMessage());
        }
        return response;
    }

    @PutMapping("/status/solicitacao/{id}")
    public ResponseEntity<Object> finalizar(@PathVariable(name = "id") Integer id) {
        ResponseEntity<Object> response;
        try {
            Optional<SolicitacaoModel> optionalSolicitacao = solicitacaoRepository.findById(id);
            if (optionalSolicitacao.isEmpty()) {
                throw new NotFoundException().toSolicitacao(id);
            }
            SolicitacaoModel solicitacao = optionalSolicitacao.get();
            solicitacaoRepository.save(solicitacao);
            response = ResponseEntity.status(201).body("Solicitação finalizada com sucesso, atual status da solicitação:: "+ solicitacao);
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao finalizar a pendencia da solicitação com o id:: "+id);
            throw new RuntimeException("Erro ao finalizar a pendencia da solicitação com o id:: "+id+":: "+ e.getMessage());
        }
        return response;
    }

    private void methodsOn(SolicitacaoModel solicitacao) {
        solicitacao.add(linkTo(methodOn(SolicitacaoController.class).finalizar(solicitacao.getId())).withRel("finalizar"));
        solicitacao.add(linkTo(methodOn(SolicitacaoController.class).listar(solicitacao.getId())).withRel("listar"));
        solicitacao.add(linkTo(methodOn(SolicitacaoController.class).listarTodos()).withRel("listarTodos"));
        solicitacao.add(linkTo(methodOn(SolicitacaoController.class).solicitarDuende(solicitacao.getSolicitante(), null)).withRel("solicitarDuende"));
        solicitacao.add(linkTo(methodOn(SolicitacaoController.class).solicitarFoto(solicitacao.getSolicitante(), null)).withRel("solicitarFoto"));
    }
}
