package com.example.projetoplanta.com.example.projetoplanta.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class ValidateException extends RuntimeException {
    
    private String mensagem;
    
}
