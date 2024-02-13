package com.example.projetoplanta.com.example.projetoplanta.DTO;

import jakarta.validation.constraints.NotBlank;

public record PlantaRecordDTO(
    @NotBlank   
    String nome, 
    String nomePop_1, 
    String nomePop_2, 
    @NotBlank   
    String genetica, 
    @NotBlank   
    String porcentagemTHC, 
    @NotBlank   
    String porcentagemCDB, 
    @NotBlank   
    String cor, 
    String aroma, 
    String terpenoDominante, 
    String terpenosSecundarios, 
    @NotBlank   
    String beneficios, 
    @NotBlank   
    String maleficios, 
    @NotBlank   
    String ajudaMedica, 
    @NotBlank   
    String paisOrigem, 
    @NotBlank   
    String alturaEmCM, 
    @NotBlank   
    String gramaPorMetroQuadrado, 
    @NotBlank   
    String tempoFloracao) {   
}
