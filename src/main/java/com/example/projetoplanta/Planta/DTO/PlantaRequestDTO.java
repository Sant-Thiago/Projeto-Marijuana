package com.example.projetoplanta.Planta.DTO;

import org.hibernate.validator.constraints.Length;


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
    
    Integer aroma_terpeno, 
    
    Integer efeito, 
    
    @NotNull
    Long responsavel,

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
