package com.example.projetoplanta.Imagem.DTO;

import jakarta.validation.constraints.NotBlank;

public record ImagemRequestDTO (
    @NotBlank   
    String fkPlanta,

    @NotBlank
    String caminho
) {}
