package com.example.projetoplanta.Aroma.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AromaRequestDTO(
    @NotBlank
    @Pattern(regexp = "Aroma|Terpeno", message = "O campo tipo deve ser 'Aroma' ou 'Terpeno'") 
    String tipo,

    @NotBlank
    String nome,

    @NotBlank
    String caracteristica
) {}
