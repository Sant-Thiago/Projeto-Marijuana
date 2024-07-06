package com.example.projetoplanta.Solicitacao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum tipoSolicitacao {
    DUENDE("DUENDE"),
    FOTO("FOTO");

    private String valor;
}
