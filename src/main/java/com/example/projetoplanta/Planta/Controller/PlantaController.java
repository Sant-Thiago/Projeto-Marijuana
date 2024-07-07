package com.example.projetoplanta.Planta.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetoplanta.Planta.DTO.PlantaDTO;
import com.example.projetoplanta.Planta.DTO.PlantaRequestDTO;
import com.example.projetoplanta.Planta.Mapper.PlantaMapper;
import com.example.projetoplanta.Planta.Module.PlantaModel;
import com.example.projetoplanta.Planta.Repository.PlantaRepository;
import com.example.projetoplanta.exceptions.NotFoundException;

import jakarta.validation.Valid;

@RestController
public class PlantaController {
    
    @Autowired
    PlantaRepository plantaRepository;

    @PostMapping("/cadastrar/planta")
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid PlantaRequestDTO plantaRequestDTO) {
        ResponseEntity<Object> response;
        try {
            PlantaModel planta = PlantaMapper.toModel(plantaRequestDTO);
            planta = plantaRepository.save(planta);
            response = ResponseEntity.status(201).body(PlantaMapper.toDTO(planta));
            // Logger.saved("Planta "+planta.getId()+" criada com sucesso.");
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao executar a criação da planta!");
            // Logger.error("Erro ao criar planta!\n\n"+ e.getMessage());
        }
        return response;
    }

    @GetMapping("/listar/plantas")
    public ResponseEntity<List<?>> listarTodas() {
        ResponseEntity<List<?>> responses;
        try {
            List<PlantaModel> listaTodasPlantas = plantaRepository.findAll();
            if (listaTodasPlantas.isEmpty()) {
                throw new NotFoundException().toPlanta();
            }
            List<PlantaDTO> plantaSelectedDTOs = new ArrayList<>();
            for (PlantaModel planta : listaTodasPlantas) {
                methodsOn(planta);
                plantaSelectedDTOs.add(PlantaMapper.toDTO(planta));
            }
            responses = ResponseEntity.status(200).body(plantaSelectedDTOs);
        } catch (NotFoundException e) {
            responses = ResponseEntity.status(404).body(List.of(e.getMensagem()));
        } catch (Exception e) {
            responses = ResponseEntity.status(400).body(List.of("Erro ao listar todos plantas!"));
        }
        return responses;
    }

    @GetMapping("/selecionar/planta/{id}")
    public ResponseEntity<Object> selecionarPlanta(@PathVariable(value = "id") String id) {
        ResponseEntity<Object> response;
        try {
            Optional<PlantaModel> optinoalPlanta = plantaRepository.findById(id);
            if (optinoalPlanta.isEmpty()) {
                throw new NotFoundException().toPlanta(id);
            }
            PlantaModel planta = optinoalPlanta.get();
            methodsOn(planta);
            response = ResponseEntity.status(200).body(PlantaMapper.toDTO(planta));
            // Logger.select("Planta "+id+" selecionada com sucesso.");
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
            // Logger.notFound("Planta "+id+" não encontrada no sistema!");
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao selecionar a planta com o id:: "+ id);
            // Logger.error("Erro ao selecionar a planta com o id:: "+id+" no sistema!\n\n"+ e.getMessage());
        }
        return response;
    }

    @PutMapping("/atualizar/planta/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable(value = "id") String id, @RequestBody @Valid PlantaRequestDTO plantaRequestDTO ) {
        ResponseEntity<Object> response;
        try {
            Optional<PlantaModel> optionalPlanta = plantaRepository.findById(id);
            if (optionalPlanta.isEmpty()) {
                throw new NotFoundException().toPlanta(id);
            }
            PlantaModel planta = PlantaMapper.toModel(plantaRequestDTO);
            methodsOn(planta);
            response = ResponseEntity.status(200).body(PlantaMapper.toDTO(planta));
        } catch (NotFoundException e) {
            response = ResponseEntity.status(404).body(e.getMensagem());
            // Logger.notFound("Planta "+id+" não encontrada no sistema!");
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao atualizar a planta com o id:: "+ id);
            // Logger.error("Erro ao atualizar a planta com o id:: "+id+" no sistema!\n\n"+ e.getMessage());
        }
        return response;
    }

    private void methodsOn(PlantaModel planta) {
        planta.add(linkTo(methodOn(PlantaController.class).cadastrar(null)).withRel("cadastrarPlanta"));
        planta.add(linkTo(methodOn(PlantaController.class).selecionarPlanta(planta.getId())).withRel("selecionarPlanta"));
        planta.add(linkTo(methodOn(PlantaController.class).listarTodas()).withRel("listarTodas"));
        planta.add(linkTo(methodOn(PlantaController.class).atualizar(planta.getId(), null)).withRel("atualizar"));
    }
}
