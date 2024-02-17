package com.example.projetoplanta.com.example.projetoplanta.modules;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.hateoas.RepresentationModel;

import com.example.projetoplanta.com.example.projetoplanta.modules.PrimaryKey.FavoritoPK;

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
    private static final long serialVersionUID = 1l;
    
    @EmbeddedId
    private FavoritoPK id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "fkUsuario")
    private UsuarioModel fkUsuario;

    @ManyToOne
    @MapsId("plantaId")
    @JoinColumn(name = "fkPlanta")
    private PlantaModel fkPlanta;
    private Timestamp data;
}
