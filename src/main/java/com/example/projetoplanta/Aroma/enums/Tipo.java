package com.example.projetoplanta.Aroma.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Tipo {
    AROMA ("AROMA"),
    TERPENO ( "TERPENO");

    private String valor;
}
