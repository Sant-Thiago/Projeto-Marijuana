package com.example.projetoplanta.Favorito.DTO;

import jakarta.validation.constraints.NotBlank;

public record FavoritoRequestDTO(
    @NotBlank
    String fkUsuario,
    @NotBlank
    String fkPlanta
) {}
