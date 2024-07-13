package com.example.projetoplanta.Planta.Mapper;

import com.example.projetoplanta.Planta.DTO.PlantaDTO;
import com.example.projetoplanta.Planta.DTO.PlantaRequestDTO;
import com.example.projetoplanta.Planta.Module.PlantaModel;

public class PlantaMapper {

    public static PlantaModel toModel(PlantaRequestDTO plantaRequestDTO) {
        PlantaModel plantaModel = new PlantaModel();
        plantaModel.setNome(plantaRequestDTO.nome());
        plantaModel.setNomePop_1(plantaRequestDTO.nomePop_1());
        plantaModel.setNomePop_2(plantaRequestDTO.nomePop_2());
        plantaModel.setGenetica(plantaRequestDTO.genetica());
        // plantaModel.setResponsavel(plantaRequestDTO.); FAZER O SELECT DO DUENDE PELO ID NO SERVICE E ENVIAR PARA O MAPPER LINDO
        plantaModel.setPorcentagemTHC(plantaRequestDTO.porcentagemTHC());
        plantaModel.setPorcentagemCDB(plantaRequestDTO.porcentagemCDB());
        plantaModel.setFkAroma_terpeno(plantaRequestDTO.aroma_terpeno());
        plantaModel.setFkEfeito(plantaRequestDTO.efeito());
        plantaModel.setPaisOrigem(plantaRequestDTO.paisOrigem());
        plantaModel.setAlturaEmCM(plantaRequestDTO.alturaEmCM());
        plantaModel.setGramaPorMetroQuadrado(plantaRequestDTO.gramaPorMetroQuadrado());
        plantaModel.setTempoFloracao(plantaRequestDTO.tempoFloracao());
        return plantaModel;
    }

    public static PlantaDTO toDTO(PlantaModel plantaModel) {
        PlantaDTO plantaDTO = new PlantaDTO(
            plantaModel.getId(),
            plantaModel.getNome(),
            plantaModel.getNomePop_1(),
            plantaModel.getNomePop_2(),
            plantaModel.getGenetica(),
            plantaModel.getPorcentagemTHC(),
            plantaModel.getPorcentagemCDB(),
            plantaModel.getFkAroma_terpeno(),
            plantaModel.getFkEfeito(),
            plantaModel.getResponsavel(),
            plantaModel.getPaisOrigem(),
            plantaModel.getAlturaEmCM(),
            plantaModel.getGramaPorMetroQuadrado(),
            plantaModel.getTempoFloracao()
        );
        return plantaDTO;
    }



}
