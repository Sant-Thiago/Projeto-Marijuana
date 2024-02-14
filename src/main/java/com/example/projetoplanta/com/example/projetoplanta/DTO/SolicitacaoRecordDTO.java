package com.example.projetoplanta.com.example.projetoplanta.DTO;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotBlank;

public record SolicitacaoRecordDTO(
    @NotBlank
    String solicitacao,
    Byte[] fotoUsuario,
    Timestamp dtArmazenamento,
    @NotBlank
    String status) {}
