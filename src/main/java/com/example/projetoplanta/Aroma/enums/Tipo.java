package com.example.projetoplanta.Aroma.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Tipo {
    AROMA ("Aroma"),
    TERPENO ( "Terpeno");

    private String valor;
}
