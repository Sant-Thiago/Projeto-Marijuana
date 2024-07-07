package com.example.projetoplanta.Favorito.DTO;

import java.sql.Timestamp;

public record FavoritoDTO(
    Integer id,
    String fkUsuario,
    String fkPlanta,
    Timestamp dataRegistro
) {}