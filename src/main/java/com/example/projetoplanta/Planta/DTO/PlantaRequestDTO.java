package com.example.projetoplanta.Planta.DTO;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PlantaRequestDTO(
    @NotBlank   
    String nome, 
    
    String nomePop_1, 
    
    String nomePop_2, 
    
    @NotBlank   
    @Pattern(regexp = "Sativa|Indica|Ruderalis|Híbrido", message = "O campo genética deve ser 'Sativa', 'Indica', 'Ruderalis' ou 'Híbrido'") 
    String genetica,
    
    @NotBlank   
    Float porcentagemTHC, 
    
    @NotBlank   
    Float porcentagemCDB, 
    
    @NotBlank
    @Length(min = 2, max = 2, message = "O campo 'paisOrigem' deve ter exatamente 2 caracteres")
    String paisOrigem, 
    
    @NotBlank   
    Float alturaEmCM, 
    
    @NotBlank   
    Float gramaPorMetroQuadrado, 
    
    @NotBlank   
    Integer tempoFloracao
) {}
