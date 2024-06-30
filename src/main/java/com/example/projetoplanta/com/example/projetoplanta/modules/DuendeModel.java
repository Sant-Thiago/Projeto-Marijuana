package com.example.projetoplanta.com.example.projetoplanta.modules;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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
@Table(name = "duende")
@EqualsAndHashCode(of = "fkUsuario", callSuper = false)
public class DuendeModel extends RepresentationModel<DuendeModel> implements Serializable{
    private static final long serialVersionUID = 2L;

    @Id
    private UsuarioModel fkUsuario;
    
    private String numeroNascionalId;

    @CreationTimestamp
    @Column(name = "dtIntegracao", nullable = false, updatable = false)
    private Timestamp dtIntegracao;
    
}
