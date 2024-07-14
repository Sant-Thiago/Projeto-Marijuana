package com.example.projetoplanta.Efeito.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EfeitoRequestDTO(    
    @NotBlank
    @Pattern(regexp = "Benefício|Malefício", message = "O campo tipo deve ser 'Benefício' ou 'Malefício'") 
    String tipo,

    @NotBlank
    String nome,

    @NotBlank
    String causa
) {}
