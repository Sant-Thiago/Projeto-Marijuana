package com.example.projetoplanta.Planta.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetoplanta.Planta.DTO.PlantaRecordDTO;
import com.example.projetoplanta.Planta.Module.PlantaModel;
import com.example.projetoplanta.Planta.Repository.PlantaRepository;

import jakarta.validation.Valid;

@RestController
public class PlantaController {
    
    @Autowired
    PlantaRepository plantaRepository;

    @PostMapping("/cadastrar/planta")
    public ResponseEntity<Object> cadastrarPlanta(@RequestBody @Valid PlantaRecordDTO planta) {
        ResponseEntity<Object> response;
        try {
            var plantaModel = new PlantaModel();
            BeanUtils.copyProperties(planta, plantaModel);
            PlantaModel plantaSaved = plantaRepository.save(plantaModel);
            response = ResponseEntity.status(201).body(plantaSaved);
        } catch (Exception e) {
            response = ResponseEntity.status(400).body("Erro ao executar a criação da planta!");
            // Logger.error(e.getMessage());
        }
        return response;
    }

    @GetMapping("/listar/plantas")
    public ResponseEntity<List<PlantaModel>> listarTodasPlantas() {
        List<PlantaModel> listaTodasPlantas = plantaRepository.findAll();
        if (!listaTodasPlantas.isEmpty()) {
            for (PlantaModel planta : listaTodasPlantas) {
                String id = planta.getId();
                planta.add(linkTo(methodOn(PlantaController.class).selecionarPlanta(id)).withRel("listar"));
                planta.add(linkTo(methodOn(PlantaController.class).atualizarPlanta(id, null)).withRel("atualizar"));

            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaTodasPlantas);
    }

    @GetMapping("/selecionar/planta/{id}")
    public ResponseEntity<Object> selecionarPlanta(@PathVariable(value = "id") String id) {
        Optional<PlantaModel> planta = plantaRepository.findById(id);
        if (planta.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Planta não encontrada.");
        }
        planta.get().add(linkTo(methodOn(PlantaController.class).listarTodasPlantas()).withRel("listarTodas"));
        planta.get().add(linkTo(methodOn(PlantaController.class).atualizarPlanta(id, null)).withRel("atualizar"));
        return ResponseEntity.status(HttpStatus.OK).body(planta.get());
    }

    @PutMapping("/atualizar/planta/{id}")
    public ResponseEntity<Object> atualizarPlanta(@PathVariable(value = "id") String id, @RequestBody @Valid PlantaRecordDTO plantaRecordDTO ) {
        Optional<PlantaModel> planta = plantaRepository.findById(id);
        if (planta.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Planta não encontrada.");
        }
        var plantaModel = planta.get();
        BeanUtils.copyProperties(plantaRecordDTO, plantaModel);
        return ResponseEntity.status(HttpStatus.OK).body("Planta atualizada: " + plantaRepository.save(plantaModel));
    }
}
