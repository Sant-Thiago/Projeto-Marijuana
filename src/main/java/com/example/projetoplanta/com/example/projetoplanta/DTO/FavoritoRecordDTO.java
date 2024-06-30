package com.example.projetoplanta.com.example.projetoplanta.DTO;

import com.example.projetoplanta.com.example.projetoplanta.modules.PlantaModel;
import com.example.projetoplanta.com.example.projetoplanta.modules.UsuarioModel;

public record FavoritoRecordDTO(
    UsuarioModel fkUsuario,
    PlantaModel fkPlanta) {}
