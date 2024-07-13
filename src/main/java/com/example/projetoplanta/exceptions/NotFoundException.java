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

    public NotFoundException toAroma() {
        setMensagem("Nenhum aroma encontrado no sistema!");
        return this;
    }

    public NotFoundException toAroma(Integer id) {
        setMensagem("Nenhum aroma com o id:: "+id+" encontrado no sistema!");
        return this;
    }

    public NotFoundException toComentario() {
        setMensagem("Nenhum comentario encontrado no sistema!");
        return this;
    }

    public NotFoundException toComentario(Integer id) {
        setMensagem("Nenhum comentario com o id:: "+id+" encontrado no sistema!");
        return this;
    }

    public NotFoundException toDuende() {
        setMensagem("Nenhum duende encontrado no sistema!");
        return this;
    }

    public NotFoundException toDuende(Long id) {
        setMensagem("Nenhum duende com o id:: "+id+" encontrado no sistema!");
        return this;
    }
    
    public NotFoundException toEfeito() {
        setMensagem("Nenhum Efeito encontrado com esses requisitos!");
        return this;
    }

    public NotFoundException toEfeito(Integer id) {
        setMensagem("Nenhum Efeito com o id:: "+id+" encontrado no sistema!");
        return this;
    }
    
    public NotFoundException toFavorito() {
        setMensagem("Nenhum Favorito encontrado com esses requisitos!");
        return this;
    }
    
    public NotFoundException toFavorito(Integer id) {
        setMensagem("Nenhum favorito com o id:: "+id+" encontrado no sistema!");
        return this;
    }

    public NotFoundException toImagem() {
        setMensagem("Nenhuma Imagem encontrado no sistema!");
        return this;
    }
    
    public NotFoundException toImagem(Integer id) {
        setMensagem("Nenhuma Imagem com o id:: "+id+" encontrada no sistema!");
        return this;
    }

    public NotFoundException toImagem(String fkPlanta) {
        setMensagem("Nenhuma Imagem da Planta:: "+fkPlanta+" encontrada no sistema!");
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
