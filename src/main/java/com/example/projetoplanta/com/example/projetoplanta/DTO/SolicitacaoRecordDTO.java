package com.example.projetoplanta.com.example.projetoplanta.DTO;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotBlank;

public record SolicitacaoRecordDTO(
    @NotBlank
    String solicitacao,
    @NotBlank
    String tipo,
    String motivo,
    Byte[] fotoUsuario, // mudar para string, para receber o endereço da imagem
    Timestamp dtArmazenamento,
    @NotBlank
    String status) {}
