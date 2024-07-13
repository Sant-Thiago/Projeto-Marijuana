package com.example.projetoplanta.Aroma.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AromaRequestDTO(
    @NotBlank
    @Pattern(regexp = "AROMA|TERPENO", message = "O campo tipo deve ser 'AROMA' ou 'TERPENO'") 
    String tipo,

    @NotBlank
    String nome,

    @NotBlank
    String caracteristica
) {}
