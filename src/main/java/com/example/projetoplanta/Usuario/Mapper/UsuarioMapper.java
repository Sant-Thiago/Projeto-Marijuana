package com.example.projetoplanta.Usuario.Mapper;

import com.example.projetoplanta.Usuario.DTO.UsuarioDTO;
import com.example.projetoplanta.Usuario.DTO.UsuarioRequestDTO;
import com.example.projetoplanta.Usuario.DTO.UsuarioSelectedDTO;
import com.example.projetoplanta.Usuario.Module.UsuarioModel;

public class UsuarioMapper {
    
    public static UsuarioModel toModel(UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setNome(usuarioRequestDTO.nome());
        usuarioModel.setEmail(usuarioRequestDTO.email());
        usuarioModel.setSenha(usuarioRequestDTO.senha());
        usuarioModel.setGenero(usuarioRequestDTO.genero());
        usuarioModel.setDtNascimento(usuarioRequestDTO.dtNascimento());
        usuarioModel.setPais(usuarioRequestDTO.pais());
        usuarioModel.setFoto(usuarioRequestDTO.foto());
        usuarioModel.setAtivo(usuarioRequestDTO.ativo());
        return usuarioModel;
    }
    
    public static UsuarioDTO toDTO(UsuarioModel usuarioModel) {
        UsuarioDTO usuarioDTO = new UsuarioDTO(
            usuarioModel.getId(),
            usuarioModel.getEmail(),
            usuarioModel.getSenha(),
            usuarioModel.getNome(),
            usuarioModel.getFoto(),
            usuarioModel.getPais(),
            usuarioModel.getDtNascimento(),
            usuarioModel.getGenero(),
            usuarioModel.getAtivo()
        );
        return usuarioDTO;
    }

    public static UsuarioSelectedDTO toSelectedDTO(UsuarioModel usuarioModel) {
        UsuarioSelectedDTO usuarioSelectedDTO = new UsuarioSelectedDTO(
            usuarioModel.getId(),
            usuarioModel.getEmail(),
            usuarioModel.getNome(),
            usuarioModel.getPais(),
            usuarioModel.getDtNascimento(),
            usuarioModel.getGenero()
        );
        return usuarioSelectedDTO;
    }
}
