package com.example.projetoplanta.Solicitacao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Tipo {
    DUENDE("DUENDE"),
    FOTO("FOTO");

    private String valor;
}
