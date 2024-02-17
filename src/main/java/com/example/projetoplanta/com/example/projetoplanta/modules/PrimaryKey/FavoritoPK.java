package com.example.projetoplanta.com.example.projetoplanta.modules.PrimaryKey;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Embeddable
public class FavoritoPK implements Serializable {
    
    private String usuarioId;
    private String plantaId;
}
