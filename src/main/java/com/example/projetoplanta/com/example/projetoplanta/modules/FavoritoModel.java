package com.example.projetoplanta.com.example.projetoplanta.modules;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.hateoas.RepresentationModel;

import com.example.projetoplanta.com.example.projetoplanta.modules.PrimaryKey.FavoritoId;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
    
    @EmbeddedId
    private FavoritoId id;

    @ManyToOne
    @MapsId("fkUsuario")
    @JoinColumn(name = "fkUsuario")
    private UsuarioModel fkUsuario;

    @ManyToOne
    @MapsId("fkPlanta")
    @JoinColumn(name = "fkPlanta")
    private PlantaModel fkPlanta;

    @CreationTimestamp
    @Column(name = "dataRegistro", nullable = false, updatable = false)
    private Timestamp dataRegistro;
}
