package com.example.projetoplanta.Solicitacao.Module;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "solicitacao")
public class SolicitacaoModel extends RepresentationModel<SolicitacaoModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "solicitante")
    private UsuarioModel solicitante;
    
    private String tipo;
    
    private String motivo;
    
    private String fotoUsuario;

    @CreationTimestamp
    @Column(name = "dtArmazenamento", nullable = false, updatable = false)
    private Timestamp dtArmazenamento;

    private String status; 
}
