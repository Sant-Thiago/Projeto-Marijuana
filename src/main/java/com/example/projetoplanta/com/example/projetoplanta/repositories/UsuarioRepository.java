package com.example.projetoplanta.com.example.projetoplanta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetoplanta.com.example.projetoplanta.modules.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, String>{
    
}
