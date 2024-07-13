package com.example.projetoplanta.Comentario.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetoplanta.Comentario.Module.ComentarioModel;

@Repository
public interface ComentarioRepository extends JpaRepository<ComentarioModel, Integer> {
    
}
