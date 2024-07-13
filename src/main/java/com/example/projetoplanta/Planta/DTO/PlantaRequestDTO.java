package com.example.projetoplanta.Planta.DTO;

import org.hibernate.validator.constraints.Length;

import com.example.projetoplanta.Aroma.Module.AromaModel;
import com.example.projetoplanta.Duende.Module.DuendeModel;
import com.example.projetoplanta.Efeito.Module.EfeitoModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PlantaRequestDTO(
    @NotBlank   
    String nome, 
    
    String nomePop_1, 
    
    String nomePop_2, 
    
    @NotBlank   
    @Pattern(regexp = "Sativa|Indica|Ruderalis|Híbrido", message = "O campo genética deve ser 'Sativa', 'Indica', 'Ruderalis' ou 'Híbrido'") 
    String genetica,
    
    @NotNull   
    Float porcentagemTHC, 
    
    @NotNull   
    Float porcentagemCDB, 
    
    @NotBlank
    AromaModel aroma_terpeno, 
    
    @NotBlank
    EfeitoModel efeito, 
    
    @NotBlank
    DuendeModel duende,

    @NotBlank
    @Length(min = 2, max = 2, message = "O campo 'paisOrigem' deve ter exatamente 2 caracteres")
    String paisOrigem, 
    
    @NotNull   
    Float alturaEmCM, 
    
    @NotNull   
    Float gramaPorMetroQuadrado, 
    
    @NotNull   
    Integer tempoFloracao
) {}
