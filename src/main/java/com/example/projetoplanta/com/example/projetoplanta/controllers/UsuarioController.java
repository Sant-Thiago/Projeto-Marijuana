package com.example.projetoplanta.com.example.projetoplanta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetoplanta.com.example.projetoplanta.repositories.UsuarioRepository;

@RestController
public class UsuarioController {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    
}
