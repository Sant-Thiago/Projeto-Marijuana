package com.example.projetoplanta.Usuario.Mapper;

import com.example.projetoplanta.Usuario.DTO.UsuarioDTO;
import com.example.projetoplanta.Usuario.DTO.UsuarioRequestDTO;
import com.example.projetoplanta.Usuario.DTO.UsuarioSelectedDTO;
import com.example.projetoplanta.Usuario.Module.UsuarioModel;

public class UsuarioMapper {
    
    public static UsuarioModel toModel(UsuarioRequestDTO usuarioDTO) {
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setNome(usuarioDTO.nome());
        usuarioModel.setEmail(usuarioDTO.email());
        usuarioModel.setSenha(usuarioDTO.senha());
        usuarioModel.setGenero(usuarioDTO.genero());
        usuarioModel.setDtNascimento(usuarioDTO.dtNascimento());
        usuarioModel.setPais(usuarioDTO.pais());
        usuarioModel.setFoto(usuarioDTO.foto());
        usuarioModel.setAtivo(usuarioDTO.ativo());
        return usuarioModel;
    }
    
    public static UsuarioModel toModelModified(UsuarioModel usuarioModel, UsuarioDTO usuarioDTO) {
        usuarioModel.setNome(usuarioDTO.nome() == null ? usuarioModel.getNome() : usuarioDTO.nome());
        usuarioModel.setEmail(usuarioDTO.email() == null ? usuarioModel.getSenha() : usuarioDTO.senha());
        usuarioModel.setSenha(usuarioDTO.senha() == null ? usuarioModel.getSenha() : usuarioDTO.senha());
        usuarioModel.setGenero(usuarioDTO.genero() == null ? usuarioModel.getGenero() : usuarioDTO.genero());
        usuarioModel.setDtNascimento(usuarioDTO.dtNascimento() == null ? usuarioModel.getDtNascimento() : usuarioDTO.dtNascimento());
        usuarioModel.setPais(usuarioDTO.pais() == null ? usuarioModel.getPais() : usuarioDTO.pais());
        usuarioModel.setFoto(usuarioDTO.foto() == null ? usuarioModel.getFoto() : usuarioDTO.foto());
        usuarioModel.setAtivo(usuarioDTO.ativo() == null ? usuarioModel.getAtivo() : usuarioDTO.ativo());
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
