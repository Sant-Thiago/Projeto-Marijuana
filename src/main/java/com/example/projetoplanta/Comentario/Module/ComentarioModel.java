package com.example.projetoplanta.Comentario.Module;

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

@NoArgsConstructor
@AllArgsConstructor
@ToString

@Getter
@Setter

@Entity
@Table(name = "comentario")
public class ComentarioModel extends RepresentationModel<ComentarioModel> implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String mensagem;

    @ManyToOne
    @JoinColumn(name = "fkUsuario")
    private UsuarioModel fkUsuario;
    
    @ManyToOne
    @JoinColumn(name = "fkPlanta")
    private PlantaModel fkPlanta;

    @CreationTimestamp
    @Column(name = "data", nullable = false, updatable = false)
    private Timestamp data;
}
