package com.example.projetoplanta.com.example.projetoplanta.modules.PrimaryKey;

import java.io.Serializable;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

@Embeddable
public class FavoritoPK implements Serializable {
    
    private String usuario;
    private String planta;
}
