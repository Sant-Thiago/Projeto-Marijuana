package com.example.projetoplanta.Solicitacao.DTO;

import java.sql.Timestamp;

import com.example.projetoplanta.Usuario.Module.UsuarioModel;

public record SolicitacaoResponseDTO(
    Integer id,
    UsuarioModel solicitante,
    String tipo,
    String motivo,
    String fotoUsuario,
    Timestamp dtArmazenamento,
    String status
) {}
