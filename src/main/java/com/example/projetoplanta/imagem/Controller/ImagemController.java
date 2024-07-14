package com.example.projetoplanta.Imagem.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetoplanta.Imagem.DTO.ImagemRequestDTO;
import com.example.projetoplanta.Imagem.Mapper.ImagemMapper;
import com.example.projetoplanta.Imagem.Module.ImagemModel;
import com.example.projetoplanta.Imagem.Repository.ImagemRepository;
import com.example.projetoplanta.exceptions.NotFoundException;

import jakarta.validation.Valid;


@RestController
public class ImagemController {
    
    @Autowired
    ImagemRepository imagemRepository;
    
    @Autowired
    ImagemMapper imagemMapper;

    @PostMapping("/def/imagem")
    public ResponseEntity<Object> defImagem(@RequestBody @Valid ImagemRequestDTO imagemRequestDTO) {
        ResponseEntity<Object> response;
        try {
            ImagemModel imagem = imagemMapper.toModel(imagemRequestDTO);
            imagem = imagemRepository.save(imagem);

            response = ResponseEntity.status(201).body(imagemMapper.toDTO(imagem));
            // Logger.saved("Imagem "+imagem.getId()+" definida com sucesso.");
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao definir a imagem!"+ e.getMessage());
            // Logger.error("Erro ao definir a imagem:: "+ imagemRequestDTO + " no sistema!\n\n"e.getMessage());
        }
        return response;
    }

    @PutMapping("/up/imagem/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable(name = "id") Integer id, @RequestBody @Valid ImagemRequestDTO imagemRequestDTO) {
        ResponseEntity<Object> response;
        try {
            Optional<ImagemModel> optionalImagem = imagemRepository.findById(id);
            if (optionalImagem.isEmpty()) {
                throw new NotFoundException().toImagem(id);
            }
            ImagemModel imagem = imagemMapper.toModel(imagemRequestDTO);
            imagem = imagemRepository.save(imagem);
            response = ResponseEntity.status(200).body(imagem);
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMessage());
            // Logger.notFound("Nenhuma Imagem com o id:: "+id+" encontrada no sistema!");
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao atualizar a Imagem com o id:: "+id+" do sistema!");
            // Logger.error("Erro ao atualizar a Imagem com o id:: "+id+" no sistema!\n\n"e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/deletar/imagem/{id}")
    public ResponseEntity<Object> deletar(@PathVariable(name = "id") Integer id) {
        ResponseEntity<Object> response;
        try {
            Optional<ImagemModel> optionalImagem = imagemRepository.findById(id);
            if (optionalImagem.isEmpty()) {
                throw new NotFoundException().toImagem(id);
            }
            imagemRepository.deleteById(id);
            response = ResponseEntity.status(200).body("Imagem com o id:: "+id+" deletada com sucesso.");
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMessage());
            // Logger.notFound("Nenhuma Imagem com o id:: "+id+" encontrada no sistema!");
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao deletar a Imagem com o id:: "+id+" do sistema!");
            // Logger.error("Erro ao deletar a Imagem com o id:: "+id+" do sistema!\n\n"e.getMessage());
        }
        return response;
    }

    @GetMapping("/listar/imagens")
    public ResponseEntity<Object> listar(@RequestParam(name = "i") Integer id) {
        ResponseEntity<Object> response;
        try {
            Optional<ImagemModel> optionalImagem = imagemRepository.findById(id);
            if (optionalImagem.isEmpty()) {
                throw new NotFoundException().toImagem(id);
            }
            ImagemModel imagem = optionalImagem.get();
            methodsOn(imagem);

            response = ResponseEntity.status(200).body(imagem);    
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao listar a Imagem!");
            // Logger.error("Erro ao listar a Imagem com o id:: "+id+" do sistema!\n\n"e.getMessage());
        }
        return response;
    }

    @GetMapping("/listar/imagens/{fkPlanta}")
    public ResponseEntity<List<?>> listarByFkPlanta(@PathVariable(name = "fkPlanta") String fkPlanta) {
        ResponseEntity<List<?>> response;
        try {
            List<ImagemModel> listaImagens = imagemRepository.findByFkPlanta(fkPlanta);
            if (listaImagens.isEmpty()) {
                throw new NotFoundException().toImagem(fkPlanta);
            }
            List<ImagemModel> imagemDTOs = new ArrayList<>();
            for (ImagemModel imagem : listaImagens) {
                methodsOn(imagem);
                imagemDTOs.add(imagem);
            }
            response = ResponseEntity.status(200).body(imagemDTOs);    
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(List.of(e.getMensagem()));
        } catch (Exception e) {
            response = ResponseEntity.status(400).body(List.of("Erro ao listar a Imagem da Planta especificada!"));
            // Logger.error("Erro ao listar a Imagem da Planta com o id:: "+fkPlanta+" no sistema!\n\n"e.getMessage());
        }
        return response;
    }

    private void methodsOn(ImagemModel imagem) {
        imagem.add(linkTo(methodOn(ImagemController.class).defImagem(null)).withRel("definirImagem"));
        imagem.add(linkTo(methodOn(ImagemController.class).atualizar(imagem.getId(), null)).withRel("atualizar"));
        imagem.add(linkTo(methodOn(ImagemController.class).listar(imagem.getId())).withRel("listarImagens"));    
        imagem.add(linkTo(methodOn(ImagemController.class).listarByFkPlanta(imagem.getFkPlanta().getId())).withRel("listarByFkPlanta"));    
        imagem.add(linkTo(methodOn(ImagemController.class).deletar(imagem.getId())).withRel("deletar"));    
    }
}
