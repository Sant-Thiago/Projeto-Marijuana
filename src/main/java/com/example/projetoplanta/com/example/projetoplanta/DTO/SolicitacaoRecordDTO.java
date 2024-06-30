package com.example.projetoplanta.com.example.projetoplanta.DTO;

import com.example.projetoplanta.com.example.projetoplanta.modules.UsuarioModel;

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
