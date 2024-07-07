package com.example.projetoplanta.Favorito.DTO;

import java.sql.Timestamp;

import com.example.projetoplanta.Planta.Module.PlantaModel;
import com.example.projetoplanta.Usuario.Module.UsuarioModel;

public record FavoritoDTO(
    Integer id,
    UsuarioModel fkUsuario,
    PlantaModel fkPlanta,
    Timestamp dataRegistro
) {}