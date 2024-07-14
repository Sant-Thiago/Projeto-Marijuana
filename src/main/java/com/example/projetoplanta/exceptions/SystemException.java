package com.example.projetoplanta.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SystemException extends RuntimeException {
    
    private String mensagem;

    {
        this.mensagem = "Erro ao deletar favorito no sistema.";
    }

    public SystemException error() {
        setMensagem("Erro gênerico");
        return this;
    }

    public SystemException error(String mensagem) {
        setMensagem(mensagem);
        return this;
    }
}
