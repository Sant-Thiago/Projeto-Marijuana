package com.example.projetoplanta.Comentario.DTO;

import jakarta.validation.constraints.NotBlank;

public record ComentarioRequestDTO(
    @NotBlank
    String mensagem,

    @NotBlank
    String fkUsuario,
    
    @NotBlank
    String fkPlanta
) {}
