package com.example.projetoplanta.Duende.DTO;

import java.sql.Timestamp;

import com.example.projetoplanta.Usuario.Module.UsuarioModel;

public record DuendeSelectedDTO(
    Long id,
    UsuarioModel fkUsuario,
    String numeroNacionalId,
    Timestamp dtIntegracao
    ) {
}
