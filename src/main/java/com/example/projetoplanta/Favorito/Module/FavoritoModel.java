package com.example.projetoplanta.Favorito.Module;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.hateoas.RepresentationModel;

import com.example.projetoplanta.Planta.Module.PlantaModel;
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
@Table(name = "favorito")
public class FavoritoModel extends RepresentationModel<FavoritoModel> implements Serializable {
    private static final long serialVersionUID = 2l;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fkUsuario")
    private UsuarioModel fkUsuario;

    @ManyToOne
    @JoinColumn(name = "fkPlanta")
    private PlantaModel fkPlanta;

    @CreationTimestamp
    @Column(name = "dataRegistro", nullable = false, updatable = false)
    private Timestamp dataRegistro;
}
