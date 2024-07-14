package com.example.projetoplanta.Duende.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.projetoplanta.Duende.DTO.DuendeRequestDTO;
import com.example.projetoplanta.Duende.DTO.DuendeSelectedDTO;
import com.example.projetoplanta.Duende.Module.DuendeModel;
import com.example.projetoplanta.Usuario.Repository.UsuarioRepository;

@Component
public class DuendeMapper {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    public DuendeModel toModel(DuendeRequestDTO duendeRequestDTO) {
        DuendeModel duendeModel = new DuendeModel();
        duendeModel.setFkUsuario(usuarioRepository.findById(duendeRequestDTO.fkUsuario()).get());
        duendeModel.setNumeroNacionalId(duendeRequestDTO.numeroNacionalId());
        return duendeModel;
    }

    public DuendeSelectedDTO toDTO(DuendeModel duendeModel) {
        DuendeSelectedDTO duendeSelectedDTO = new DuendeSelectedDTO(
            duendeModel.getId(),
            duendeModel.getFkUsuario(),
            duendeModel.getNumeroNacionalId(),
            duendeModel.getDtIntegracao()
        );
        return duendeSelectedDTO;
    }
}
