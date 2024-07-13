package com.example.projetoplanta.Planta.DTO;

import com.example.projetoplanta.Duende.Module.DuendeModel;

public record PlantaDTO(
    String id,
    String nome, 
    String nomePop_1, 
    String nomePop_2,     
    String genetica,
    Float porcentagemTHC, 
    Float porcentagemCDB, 
    DuendeModel responsavel,
    String paisOrigem, 
    Float alturaEmCM, 
    Float gramaPorMetroQuadrado, 
    Integer tempoFloracao
) {}
