package com.example.projetoplanta.com.example.projetoplanta.modules;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "usuario")
@EqualsAndHashCode(of="id", callSuper = false)
public class UsuarioModel extends RepresentationModel<UsuarioModel> implements Serializable {
    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private String id;
    private String email;
    private String senha;
    private String nome;
    private String foto;
    private String pais;
    private Date dtNascimento;
    private String genero;
    private Boolean ativo;
    
}
