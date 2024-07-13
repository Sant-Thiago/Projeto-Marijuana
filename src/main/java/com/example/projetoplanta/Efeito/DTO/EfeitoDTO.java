package com.example.projetoplanta.Efeito.DTO;

import com.example.projetoplanta.Aroma.enums.Tipo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record EfeitoDTO (
    
    @NotNull
    Integer id,
    
    @NotBlank
    @Pattern(regexp = "Benefício|Malefício", message = "O campo tipo deve ser 'Benefício' ou 'Malefício'") 
    Tipo tipo,

    @NotBlank
    String nome,

    @NotBlank
    String causa) {}
