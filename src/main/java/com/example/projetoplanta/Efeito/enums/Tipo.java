package com.example.projetoplanta.Efeito.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Tipo {
    BENEFICIO ("Benefício"),
    MALEFICIO ( "Malefício");

    private String valor;
}
