package com.example.projetoplanta.Planta.DTO;

import jakarta.validation.constraints.NotBlank;

public record PlantaRecordDTO(
    @NotBlank   
    String nome, 
    
    String nomePop_1, 
    
    String nomePop_2, 
    
    @NotBlank   
    String genetica, //Sativa
    
    @NotBlank   
    String porcentagemTHC, 
    
    @NotBlank   
    String porcentagemCDB, 
    
    @NotBlank   
    String paisOrigem, 
    
    @NotBlank   
    String alturaEmCM, 
    
    @NotBlank   
    String gramaPorMetroQuadrado, 
    
    @NotBlank   
    String tempoFloracao) {   
}
