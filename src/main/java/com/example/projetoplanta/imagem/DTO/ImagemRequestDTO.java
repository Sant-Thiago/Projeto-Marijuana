package com.example.projetoplanta.imagem.DTO;

import java.sql.Timestamp;

import com.example.projetoplanta.Planta.Module.PlantaModel;

import jakarta.validation.constraints.NotBlank;

public record ImagemRequestDTO(
    @NotBlank    
    PlantaModel fkPlanta,
    
    @NotBlank
    String caminho,

    @NotBlank
    Timestamp dtArmazenamento
) {}
