package com.example.projetoplanta.com.example.projetoplanta.DTO;

import jakarta.validation.constraints.NotBlank;

public record DuendeRecordDTO (
    @NotBlank    
    String fkUsuario,
    
    @NotBlank
    String numeroNacionalId) {}
