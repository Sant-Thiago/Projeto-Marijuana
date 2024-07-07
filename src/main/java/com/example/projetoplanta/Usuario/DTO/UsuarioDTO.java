package com.example.projetoplanta.Usuario.DTO;

import java.sql.Date;

public record UsuarioDTO(
    String id,
    String email,
    String senha,
    String nome, 
    String foto,    
    String pais, 
    Date dtNascimento, 
    String genero,    
    Boolean ativo
) {}
