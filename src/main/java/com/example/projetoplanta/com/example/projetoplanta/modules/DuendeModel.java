package com.example.projetoplanta.com.example.projetoplanta.modules;

import java.io.Serializable;

import org.hibernate.annotations.GeneratedColumn;
import org.springframework.aot.generate.Generated;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

@Entity
@Table(name = "duende")
@EqualsAndHashCode(of = "fkUsuario", callSuper = false)
public class DuendeModel extends RepresentationModel<DuendeModel> implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @ForeignKey
    private String fkUsuario;
    private String numeroNascionalId;
    // private Time dtIntegracao
    
}
