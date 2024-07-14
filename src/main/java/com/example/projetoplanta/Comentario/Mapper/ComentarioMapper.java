package com.example.projetoplanta.Comentario.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.projetoplanta.Comentario.DTO.ComentarioRequestDTO;
import com.example.projetoplanta.Comentario.DTO.ComentarioResponseDTO;
import com.example.projetoplanta.Comentario.Module.ComentarioModel;
import com.example.projetoplanta.Planta.Repository.PlantaRepository;
import com.example.projetoplanta.Usuario.Repository.UsuarioRepository;

@Component
public class ComentarioMapper {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PlantaRepository plantaRepository;

    public ComentarioModel toModel(ComentarioRequestDTO comentarioRequestDTO) {
        ComentarioModel comentarioModel = new ComentarioModel();
        comentarioModel.setMensagem(comentarioRequestDTO.mensagem());
        comentarioModel.setFkUsuario(usuarioRepository.findById(comentarioRequestDTO.fkUsuario()).get());
        comentarioModel.setFkPlanta(plantaRepository.findById(comentarioRequestDTO.fkPlanta()).get());
        return comentarioModel;
    }

    public ComentarioResponseDTO toDTO(ComentarioModel comentarioModel) {
        ComentarioResponseDTO comentarioResponseDTO = new ComentarioResponseDTO(
            comentarioModel.getId(),
            comentarioModel.getMensagem(),
            comentarioModel.getFkUsuario(),
            comentarioModel.getFkPlanta(),
            comentarioModel.getData()
        );
        return comentarioResponseDTO;
    }
}
