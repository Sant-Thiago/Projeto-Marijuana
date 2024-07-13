package com.example.projetoplanta.Aroma.Module;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.example.projetoplanta.Aroma.enums.Tipo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "aroma_terpeno")
public class AromaModel extends RepresentationModel<AromaModel> implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Enumerated(EnumType.STRING) // Mapeamento do enum para o tipo STRING
    private Tipo tipo;

    private String nome;

    private String caracteristica;   
}
