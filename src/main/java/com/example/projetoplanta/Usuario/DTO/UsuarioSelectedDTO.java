package com.example.projetoplanta.Usuario.DTO;

import java.sql.Date;


public record UsuarioSelectedDTO(
    String id,
    String email,
    String nome, 
    String foto,
    String pais, 
    Date dtNascimento, 
    String genero    
) {}
