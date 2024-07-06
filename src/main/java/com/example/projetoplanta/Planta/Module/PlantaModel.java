package com.example.projetoplanta.Planta.Module;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "planta")
@EqualsAndHashCode(of="id", callSuper = false)
public class PlantaModel extends RepresentationModel<PlantaModel> implements Serializable {
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private String id;
    private String nome;
    private String nomePop_1;
    private String nomePop_2;
    private String genetica;
    private String porcentagemTHC;
    private String porcentagemCDB;
    private String paisOrigem;
    private String alturaEmCM;
    private String gramaPorMetroQuadrado;
    private String tempoFloracao;
}
