package com.example.projetoplanta.Efeito.Module;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString

@Getter
@Setter

@Entity
@Table(name = "efeito")
public class EfeitoModel extends RepresentationModel<EfeitoModel> implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String tipo;

    private String nome;

    private String causa;   
}
