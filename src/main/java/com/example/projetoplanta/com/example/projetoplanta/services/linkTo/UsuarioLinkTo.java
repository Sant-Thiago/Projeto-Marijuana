package com.example.projetoplanta.com.example.projetoplanta.services.linkTo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.projetoplanta.com.example.projetoplanta.controllers.UsuarioController;
import com.example.projetoplanta.com.example.projetoplanta.modules.UsuarioModel;

public class UsuarioLinkTo implements ILinkTo {
    
    UsuarioModel usuario;

    public UsuarioLinkTo(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public UsuarioLinkTo() {}

    @Override
    public void methodsOn() {
        usuario.add(linkTo(methodOn(UsuarioController.class).deletarUsuario(usuario.getId())).withRel("deletar"));
        usuario.add(linkTo(methodOn(UsuarioController.class).listarTodosUsuarios()).withRel("listarTodos"));
        usuario.add(linkTo(methodOn(UsuarioController.class).listarUsuario(usuario.getId())).withRel("listar"));
        usuario.add(linkTo(methodOn(UsuarioController.class).modificarUsuario(usuario.getId(), null)).withRel("modificarUsuário"));
        usuario.add(linkTo(methodOn(UsuarioController.class).statusUsuario(usuario.getId())).withRel("ativar"));
    }

}
