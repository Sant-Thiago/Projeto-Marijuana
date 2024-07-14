package com.example.projetoplanta.Planta.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.projetoplanta.Aroma.Repository.AromaRepository;
import com.example.projetoplanta.Duende.Repository.DuendeRepository;
import com.example.projetoplanta.Efeito.Repository.EfeitoRepository;
import com.example.projetoplanta.Planta.DTO.PlantaDTO;
import com.example.projetoplanta.Planta.DTO.PlantaRequestDTO;
import com.example.projetoplanta.Planta.Module.PlantaModel;

@Component
public class PlantaMapper {

    @Autowired
    AromaRepository aromaRepository;
    @Autowired
    EfeitoRepository efeitoRepository;
    @Autowired
    DuendeRepository duendeRepository;

    public PlantaDTO toDTO(PlantaModel plantaModel) {
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

    public PlantaModel toModel(PlantaRequestDTO plantaRequestDTO) {
        PlantaModel plantaModel = new PlantaModel();
        plantaModel.setNome(plantaRequestDTO.nome());
        plantaModel.setNomePop_1(plantaRequestDTO.nomePop_1());
        plantaModel.setNomePop_2(plantaRequestDTO.nomePop_2());
        plantaModel.setGenetica(plantaRequestDTO.genetica());
        plantaModel.setPorcentagemTHC(plantaRequestDTO.porcentagemTHC());
        plantaModel.setPorcentagemCDB(plantaRequestDTO.porcentagemCDB());
        if (plantaRequestDTO.aroma_terpeno() != null) plantaModel.setFkAroma_terpeno(aromaRepository.findById(plantaRequestDTO.aroma_terpeno()).get());
        if (plantaRequestDTO.efeito() != null) plantaModel.setFkEfeito(efeitoRepository.findById(plantaRequestDTO.efeito()).get());
        plantaModel.setResponsavel(duendeRepository.findById(plantaRequestDTO.responsavel()).get()); // FAZER O SELECT DO DUENDE PELO ID NO SERVICE E ENVIAR PARA O MAPPER LINDO
        plantaModel.setPaisOrigem(plantaRequestDTO.paisOrigem());
        plantaModel.setAlturaEmCM(plantaRequestDTO.alturaEmCM());
        plantaModel.setGramaPorMetroQuadrado(plantaRequestDTO.gramaPorMetroQuadrado());
        plantaModel.setTempoFloracao(plantaRequestDTO.tempoFloracao());
        return plantaModel;
    }

    public PlantaModel toUpdateModel(PlantaRequestDTO plantaRequestDTO, PlantaModel plantaModel) {
        PlantaModel newPlantaModel = new PlantaModel();
        newPlantaModel.setId(plantaModel.getId());
        newPlantaModel.setNome(plantaRequestDTO.nome());
        newPlantaModel.setNomePop_1(plantaRequestDTO.nomePop_1());
        newPlantaModel.setNomePop_2(plantaRequestDTO.nomePop_2());
        newPlantaModel.setGenetica(plantaRequestDTO.genetica());
        newPlantaModel.setPorcentagemTHC(plantaRequestDTO.porcentagemTHC());
        newPlantaModel.setPorcentagemCDB(plantaRequestDTO.porcentagemCDB());
        if (plantaRequestDTO.aroma_terpeno() != null) newPlantaModel.setFkAroma_terpeno(aromaRepository.findById(plantaRequestDTO.aroma_terpeno()).get());
        if (plantaRequestDTO.efeito() != null) newPlantaModel.setFkEfeito(efeitoRepository.findById(plantaRequestDTO.efeito()).get());
        newPlantaModel.setResponsavel(duendeRepository.findById(plantaRequestDTO.responsavel()).get()); // FAZER UMA TABELA SOMENTE PARA AS MODIFICAÇÕES 
        newPlantaModel.setPaisOrigem(plantaRequestDTO.paisOrigem());
        newPlantaModel.setAlturaEmCM(plantaRequestDTO.alturaEmCM());
        newPlantaModel.setGramaPorMetroQuadrado(plantaRequestDTO.gramaPorMetroQuadrado());
        newPlantaModel.setTempoFloracao(plantaRequestDTO.tempoFloracao());
        return newPlantaModel;
    }
}
