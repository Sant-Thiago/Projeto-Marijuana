package com.example.projetoplanta.com.example.projetoplanta.services.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class DadoNaoEncontradoException extends RuntimeException {

    private String mensagem;

}
