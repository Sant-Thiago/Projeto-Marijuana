package com.example.projetoplanta.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import com.example.projetoplanta.com.example.projetoplanta.utils.Validate;

public class ValidateTest {
     
    @Test
    public static void testPasswordValid() {
        assertTrue(Validate.password("SENHAValida123"));
        assertTrue(Validate.password("NoMinimo8&Conter$imbolo"));
        assertTrue(Validate.password("12345SenhaCerta"));
    }

    @Test
    public static void testPasswordInvalid() {
        assertTrue(Validate.password("senhafacil"), "Senha inválida! Senha não contém letras Maiúsculas, Números e/ou Símbolos.");
        assertTrue(Validate.password("123"), "Senha inválida! Senha deve ter no mínimo 8 caracteres.");
        assertTrue(Validate.password("SENHA!123"), "Senha inválida! Senha deve ter no mínimo um caracter Minúsculo");
    }

    @Test
    public static void testDateValid() {
        
        assertTrue(Validate.date());
        assertTrue(Validate.date("NoMinimo8&Conter$imbolo"));
        assertTrue(Validate.date("12345SenhaCerta"));
    }
    public static boolean date(Date data) {
        boolean valid = false;
        String regex = "^\\d{2}/\\d{2}/\\d{4}$";
        if (String.valueOf(data).matches(regex)) {
            valid = true;
        }
        return valid;
    }
}
