package com.example.projetoplanta.Aroma.DTO;

import com.example.projetoplanta.Aroma.enums.Tipo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AromaDTO (
    
    @NotNull
    Integer id,
    
    @NotBlank
    @Pattern(regexp = "AROMA|TERPENO", message = "O campo tipo deve ser 'AROMA' ou 'TERPENO'") 
    Tipo tipo,

    @NotBlank
    String nome,

    @NotBlank
    String caracteristica) {}
