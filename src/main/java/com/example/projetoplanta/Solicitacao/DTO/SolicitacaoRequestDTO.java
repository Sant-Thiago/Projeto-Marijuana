package com.example.projetoplanta.Solicitacao.DTO;


import jakarta.validation.constraints.NotBlank;

public record SolicitacaoRequestDTO(
    @NotBlank
    String tipo,
    
    @NotBlank
    String motivo,
    
    String fotoUsuario
    
    // @NotBlank
    // String status
) {}
