package com.example.projetoplanta.Favorito.DTO;

import com.example.projetoplanta.Planta.Module.PlantaModel;
import com.example.projetoplanta.Usuario.Module.UsuarioModel;

public record FavoritoRecordDTO(
    UsuarioModel fkUsuario,
    PlantaModel fkPlanta) {}
