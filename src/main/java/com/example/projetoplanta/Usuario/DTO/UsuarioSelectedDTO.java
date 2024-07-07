package com.example.projetoplanta.Usuario.DTO;

import java.sql.Date;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public record UsuarioSelectedDTO(
    @NotBlank
    String id,
    
    @NotBlank(message = "O email não pode estar em branco")  
    String email,

    @NotBlank(message = "O nome não pode estar em branco")
    String nome, 
   
    @Length(min = 2, max = 2, message = "O campo 'pais' deve ter exatamente 2 caracteres")
    String pais, 
   
    @NotBlank
    Date dtNascimento, 
   
    @Length(min = 3, max = 3, message = "O campo 'genero' deve ter exatamente 3 caracteres")
    String genero    
) {}
