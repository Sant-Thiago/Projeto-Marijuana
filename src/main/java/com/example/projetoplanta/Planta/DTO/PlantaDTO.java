package com.example.projetoplanta.Planta.DTO;

import com.example.projetoplanta.Aroma.Module.AromaModel;
import com.example.projetoplanta.Duende.Module.DuendeModel;
import com.example.projetoplanta.Efeito.Module.EfeitoModel;

public record PlantaDTO(
    String id,
    String nome, 
    String nomePop_1, 
    String nomePop_2,     
    String genetica,
    Float porcentagemTHC, 
    Float porcentagemCDB,
    AromaModel aroma_terpeno, 
    EfeitoModel efeito, 
    DuendeModel responsavel,
    String paisOrigem, 
    Float alturaEmCM, 
    Float gramaPorMetroQuadrado, 
    Integer tempoFloracao
) {}
