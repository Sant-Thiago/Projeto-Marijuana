package com.example.projetoplanta.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class NotFoundException extends RuntimeException {

    private String mensagem;

    public NotFoundException toDuende() {
        setMensagem("Nenhum duende não encontrado no sistema!");
        return this;
    }

    public NotFoundException toDuende(String id) {
        setMensagem("Nenhum duende com o id:: "+id+" encontrado no sistema!");
        return this;
    }
    
    public NotFoundException toFavorito() {
        setMensagem("Nenhum Favorito encontrado com esses requisitos!");
        return this;
    }
    
    public NotFoundException toFavorito(Integer id) {
        setMensagem("Nenhum favorito encontrado com o id:: "+id+" encontrado no sistema!");
        return this;
    }
    
    public NotFoundException toFavorito(String fkPlanta, String fkUsuario) {
        setMensagem("Planta com o id:: "+fkPlanta+" do Usuário com o id:: "+fkUsuario+" não foi encontrada na lista de Favoritos!");
        return this;
    }
    
    public NotFoundException toPlanta() {
        setMensagem("Nenhuma planta encontrada no sistema!");
        return this;
    }

    public NotFoundException toPlanta(String id) {
        setMensagem("Nenhuma planta com o id:: "+id+" encontrada!");
        return this;
    }

    public NotFoundException toSolicitacao() {
        setMensagem("Nenhum solicitação feita até o momento.");
        return this;
    }

    public NotFoundException toSolicitacao(Integer id) {
        setMensagem("Solicitação com o id:: "+id+" não encontrada!");
        return this;
    }
    
    public NotFoundException toUsuario() {
        setMensagem("Nenhum usuário não encontrado!!");
        return this;
    }

    public NotFoundException toUsuario(String id) {
        setMensagem("Nenhum usuário com o id:: "+id+" não encontrado!");
        return this;
    }

}
