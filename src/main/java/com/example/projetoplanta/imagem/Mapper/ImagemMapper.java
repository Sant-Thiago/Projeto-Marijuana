package com.example.projetoplanta.Imagem.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.projetoplanta.Imagem.DTO.ImagemRequestDTO;
import com.example.projetoplanta.Imagem.DTO.ImagemResponseDTO;
import com.example.projetoplanta.Imagem.Module.ImagemModel;
import com.example.projetoplanta.Planta.Module.PlantaModel;
import com.example.projetoplanta.Planta.Repository.PlantaRepository;

@Component
public class ImagemMapper {
    
    @Autowired
    PlantaRepository plantaRepository;

    public ImagemModel toModel(ImagemRequestDTO imagemRequestDTO) {
        ImagemModel imagemModel = new ImagemModel();
        PlantaModel plantaModel = plantaRepository.findById(imagemRequestDTO.fkPlanta()).get();
        imagemModel.setFkPlanta(plantaModel);
        imagemModel.setCaminho(imagemRequestDTO.caminho());
        return imagemModel;
    }
    
    public ImagemResponseDTO toDTO(ImagemModel imagemModel) {
        ImagemResponseDTO imagemResponseDTO = new ImagemResponseDTO(
            imagemModel.getId(),
            imagemModel.getFkPlanta(),
            imagemModel.getCaminho(),
            imagemModel.getDtArmazenamento()
        );
        return imagemResponseDTO;
    }
}
