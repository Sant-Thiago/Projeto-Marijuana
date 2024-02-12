package com.example.projetoplanta.com.example.projetoplanta.DTO;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.validation.constraints.NotBlank;

public record UsuarioRecordDTO(@NotBlank @UniqueElements String email, @NotBlank String senha, @NotBlank String nome, @NotBlank String pais, String idade, String genero) {
    
}
