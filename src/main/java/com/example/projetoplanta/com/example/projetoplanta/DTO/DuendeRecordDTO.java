package com.example.projetoplanta.com.example.projetoplanta.DTO;

import jakarta.validation.constraints.NotBlank;

public record DuendeRecordDTO (
    String fkUsuario,
    @NotBlank
    String numeroNascionalId
    // private Time dtIntegracao
) {
    
}
