package com.example.projetoplanta.Planta.DTO;

public record PlantaDTO(
    String id,
    String nome, 
    String nomePop_1, 
    String nomePop_2,     
    String genetica,
    Float porcentagemTHC, 
    Float porcentagemCDB, 
    String paisOrigem, 
    Float alturaEmCM, 
    Float gramaPorMetroQuadrado, 
    Integer tempoFloracao
) {}
