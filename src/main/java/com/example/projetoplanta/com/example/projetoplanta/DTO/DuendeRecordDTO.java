package com.example.projetoplanta.com.example.projetoplanta.DTO;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotBlank;

public record DuendeRecordDTO (
    String fkUsuario,
    @NotBlank
    String numeroNascionalId,
    Timestamp dtIntegracao) {
    
}
