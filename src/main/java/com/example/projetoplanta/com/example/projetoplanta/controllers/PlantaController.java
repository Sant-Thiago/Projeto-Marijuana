package com.example.projetoplanta.com.example.projetoplanta.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetoplanta.com.example.projetoplanta.DTO.PlantaRecordDTO;
import com.example.projetoplanta.com.example.projetoplanta.modules.PlantaModel;
import com.example.projetoplanta.com.example.projetoplanta.repositories.PlantaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/planta")
public class PlantaController {
    
    @Autowired
    PlantaRepository plantaRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity<PlantaModel> cadastrarPlanta(@RequestBody @Valid PlantaRecordDTO planta) {
        var plantaModel = new PlantaModel();
        BeanUtils.copyProperties(planta, plantaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(plantaRepository.save(plantaModel));
    }

    @GetMapping
    public ResponseEntity<List<PlantaModel>> listarTodasPlantas() {
        List<PlantaModel> listaTodasPlantas = plantaRepository.findAll();
        if (!listaTodasPlantas.isEmpty()) {
            for (PlantaModel planta : listaTodasPlantas) {
                String id = planta.getId();
                planta.add(linkTo(methodOn(PlantaController.class).listarPlanta(id)).withSelfRel());
                planta.add(linkTo(methodOn(PlantaController.class, atualizarPlanta(id, null))).withSelfRel());

            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaTodasPlantas);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Object> listarPlanta(@PathVariable(value = "id") String id) {
        Optional<PlantaModel> planta = plantaRepository.findById(id);
        if (planta.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Planta não encontrada.");
        }
        planta.get().add(linkTo(methodOn(PlantaController.class).listarTodasPlantas()).withSelfRel());
        planta.get().add(linkTo(methodOn(PlantaController.class, atualizarPlanta(id, null))).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(planta.get());
    }

    @PutMapping("/atualizar/{id}")
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
