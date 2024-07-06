package com.example.projetoplanta.Usuario.DTO;

import java.sql.Date;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UsuarioRecordDTO(
    @NotBlank(message = "O email não pode estar em branco")  
    String email,

    @NotBlank(message = "A senha não pode estar em branco")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d\\W]).{8,}$", message = "A senha não atende aos critérios mínimos de segurança")
    String senha,

    @NotBlank(message = "O nome não pode estar em branco")
    String nome, 
    
    String foto,
    
    @Length(min = 2, max = 2, message = "O campo 'pais' deve ter exatamente 2 caracteres")
    String pais, 
    
    Date dtNascimento, 
    
    @Length(min = 3, max = 3, message = "O campo 'genero' deve ter exatamente 3 caracteres")
    String genero,
    
    Boolean ativo) {}
