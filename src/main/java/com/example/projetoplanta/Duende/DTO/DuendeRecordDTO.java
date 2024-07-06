package com.example.projetoplanta.Duende.DTO;

import jakarta.validation.constraints.NotBlank;

public record DuendeRecordDTO (
    @NotBlank    
    String fkUsuario,
    
    @NotBlank
    String numeroNacionalId) {}
