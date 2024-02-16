package com.example.projetoplanta.com.example.projetoplanta.modules;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "favortito")
public class FavoritoModel extends RepresentationModel<FavoritoModel> implements Serializable {
    private static final long serialVersionUID = 1l;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String mensagem;
    @ManyToOne
    private String fkUsuario;
    @ManyToOne
    private String fkPlanta;
    private Timestamp data;
}
