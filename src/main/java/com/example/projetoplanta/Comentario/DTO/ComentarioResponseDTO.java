package com.example.projetoplanta.Comentario.DTO;

import java.sql.Timestamp;

import com.example.projetoplanta.Planta.Module.PlantaModel;
import com.example.projetoplanta.Usuario.Module.UsuarioModel;

public record ComentarioResponseDTO (   
    Integer id,
    String mensagem,
    UsuarioModel fkUsuario,
    PlantaModel fkPlanta,
    Timestamp data
) {}
