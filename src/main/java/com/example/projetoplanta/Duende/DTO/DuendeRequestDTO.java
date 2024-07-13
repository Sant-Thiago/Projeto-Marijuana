package com.example.projetoplanta.Duende.DTO;

import jakarta.validation.constraints.NotBlank;

public record DuendeRequestDTO (
    @NotBlank    
    String fkUsuario,
    
    @NotBlank
    String numeroNacionalId) {}
