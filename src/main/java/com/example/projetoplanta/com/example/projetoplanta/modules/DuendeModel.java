package com.example.projetoplanta.com.example.projetoplanta.modules;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Entity;
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
    private String fkUsuario;
    private String numeroNascionalId;
    @CreationTimestamp
    private Timestamp dtIntegracao;
    
}
