package com.example.projetoplanta.Comentario.DTO;

import java.sql.Timestamp;

import com.example.projetoplanta.Planta.Module.PlantaModel;
import com.example.projetoplanta.Usuario.Module.UsuarioModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ComentarioDTO (
    
    @NotNull
    Integer id,
    
    @NotBlank
    String mensagem,

    @NotBlank
    UsuarioModel fkUsuario,
    
    @NotBlank
    PlantaModel fkPlanta,
    
    Timestamp data) {}
