package com.example.projetoplanta.Duende.Module;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.hateoas.RepresentationModel;

import com.example.projetoplanta.Usuario.Module.UsuarioModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@EqualsAndHashCode(of = "id", callSuper = false)
public class DuendeModel extends RepresentationModel<DuendeModel> implements Serializable{
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fkUsuario")
    private UsuarioModel fkUsuario;
    
    private String numeroNacionalId;

    @CreationTimestamp
    @Column(name = "dtIntegracao", nullable = false, updatable = false)
    private Timestamp dtIntegracao;
    
}
