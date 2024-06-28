package com.example.projetoplanta.com.example.projetoplanta.utils;

public class Valid {
    public static boolean isEmail(String email) {
        boolean valid = false;
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (email.matches(regex)) {
            valid = true;
        }
        return valid;
    }    
}
