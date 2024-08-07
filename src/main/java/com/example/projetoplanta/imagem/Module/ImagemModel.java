package com.example.projetoplanta.Imagem.Module;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.hateoas.RepresentationModel;

import com.example.projetoplanta.Planta.Module.PlantaModel;

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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "imagemPlanta")
@EqualsAndHashCode(of = "id", callSuper = false)
public class ImagemModel extends RepresentationModel<ImagemModel> implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fkPlanta")
    private PlantaModel fkPlanta;

    private String caminho;
    
    @CreationTimestamp
    @Column(name = "dtArmazenamento", nullable = false, updatable = false)
    private Timestamp dtArmazenamento;
}
