package com.example.projetoplanta.imagem.Mapper;

import org.springframework.stereotype.Component;

import com.example.projetoplanta.imagem.DTO.ImagemDTO;
import com.example.projetoplanta.imagem.DTO.ImagemRequestDTO;
import com.example.projetoplanta.imagem.Module.ImagemModel;

@Component
public class ImagemMapper {
    
    public static ImagemModel toModel(ImagemRequestDTO imagemRequestDTO) {
        ImagemModel imagemModel = new ImagemModel();
        imagemModel.setFkPlanta(imagemRequestDTO.fkPlanta());
        imagemModel.setCaminho(imagemRequestDTO.caminho());
        // imagemModel.setDtArmazenamento(imagemRequestDTO.dtArmazenamento());
        return imagemModel;
    }
    
    public static ImagemDTO toDTO(ImagemModel imagemModel) {
        ImagemDTO imagemDTO = new ImagemDTO(
            imagemModel.getId(),
            imagemModel.getFkPlanta(),
            imagemModel.getCaminho(),
            imagemModel.getDtArmazenamento()
        );
        return imagemDTO;
    }
}
