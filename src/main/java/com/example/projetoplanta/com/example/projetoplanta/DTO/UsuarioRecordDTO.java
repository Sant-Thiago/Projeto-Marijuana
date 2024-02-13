package com.example.projetoplanta.com.example.projetoplanta.DTO;


import jakarta.validation.constraints.NotBlank;

public record UsuarioRecordDTO(
    @NotBlank  
    String email, 
    @NotBlank   
    String senha, 
    @NotBlank   
    String nome, 
    Byte[] foto,
    String pais, 
    String dtNascimento, 
    String genero, 
    String status) {
    
}
