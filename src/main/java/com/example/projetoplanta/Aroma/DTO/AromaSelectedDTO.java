package com.example.projetoplanta.Aroma.DTO;

import com.example.projetoplanta.Aroma.enums.Tipo;

public record AromaSelectedDTO (
    Integer id,
    Tipo tipo,
    String nome,
    String caracteristica
) {}
