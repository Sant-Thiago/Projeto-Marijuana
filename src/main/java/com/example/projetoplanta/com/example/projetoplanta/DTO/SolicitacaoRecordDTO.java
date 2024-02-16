package com.example.projetoplanta.com.example.projetoplanta.DTO;

import java.sql.Timestamp;

import com.example.projetoplanta.com.example.projetoplanta.modules.UsuarioModel;

import jakarta.validation.constraints.NotBlank;

public record SolicitacaoRecordDTO(
    @NotBlank
    UsuarioModel solicitante,
    @NotBlank
    String tipo,
    String motivo,
    Byte[] fotoUsuario, // mudar para string, para receber o endereço da imagem
    Timestamp dtArmazenamento,
    @NotBlank
    String status) {}
