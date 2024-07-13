package com.example.projetoplanta.Comentario.Mapper;

import com.example.projetoplanta.Comentario.DTO.ComentarioDTO;
import com.example.projetoplanta.Comentario.Module.ComentarioModel;

public class ComentarioMapper {
    
    public static ComentarioModel toModel(ComentarioDTO comentarioDTO) {
        ComentarioModel comentarioModel = new ComentarioModel();
        comentarioModel.setMensagem(comentarioDTO.mensagem());
        comentarioModel.setFkUsuario(comentarioDTO.fkUsuario());
        comentarioModel.setFkPlanta(comentarioDTO.fkPlanta());
        comentarioModel.setData(comentarioDTO.data());
        return comentarioModel;
    }
}
