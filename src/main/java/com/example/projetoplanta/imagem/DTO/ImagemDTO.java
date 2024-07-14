package com.example.projetoplanta.Imagem.DTO;

import java.sql.Timestamp;

import com.example.projetoplanta.Planta.Module.PlantaModel;

public record ImagemDTO(
    Integer id,
    PlantaModel fkPlanta,
    String caminho,
    Timestamp dtArmazenamento
) {}