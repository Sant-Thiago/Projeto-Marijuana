package com.example.projetoplanta.Solicitacao.DTO;

import com.example.projetoplanta.Usuario.Module.UsuarioModel;

import jakarta.validation.constraints.NotBlank;

public record SolicitacaoRecordDTO(
    @NotBlank
    UsuarioModel solicitante,
    
    @NotBlank
    String tipo,
    
    String motivo,
    
    String fotoUsuario,
    
    @NotBlank
    String status) {}
