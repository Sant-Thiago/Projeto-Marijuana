package com.example.projetoplanta.com.example.projetoplanta.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class UsuarioNaoEncontradoException extends RuntimeException {

    private String mensagem;

}
