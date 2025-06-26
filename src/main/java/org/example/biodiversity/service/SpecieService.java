package org.example.biodiversity.service;

import org.example.biodiversity.dto.SpecieReceiveDto;
import org.example.biodiversity.dto.SpecieResponseDto;
import org.example.biodiversity.entity.Observation;
import org.example.biodiversity.entity.Specie;
import org.example.biodiversity.exception.NotFoundException;
import org.example.biodiversity.repository.SpecieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecieService {

    private final SpecieRepository specieRepository;

    public SpecieService(SpecieRepository specieRepository) {
        this.specieRepository = specieRepository;
    }

    public SpecieResponseDto create(SpecieReceiveDto specieReceiveDto){
        return specieRepository.save(specieReceiveDto.dtoToEntity()).entityToDto();
    }

    public SpecieResponseDto get(Long id){ return specieRepository.findById(id).orElseThrow(NotFoundException::new).entityToDto(); }

    public List<SpecieResponseDto> get(){
        return specieRepository.findAll().stream().map(Specie::entityToDto).toList();
    }

    public SpecieResponseDto update(Long id, SpecieReceiveDto SpecieReceiveDto){
        Specie specieToUpdate = specieRepository.findById(id).orElseThrow(NotFoundException::new);
        Specie specieGet = SpecieReceiveDto.dtoToEntity();
        specieToUpdate.setCommonName(specieGet.getCommonName());
        specieToUpdate.setScientificName(specieGet.getScientificName());
        specieToUpdate.setCategory(specieGet.getCategory());
        return specieRepository.save(specieToUpdate).entityToDto();
    }

    public void delete(Long id){ specieRepository.deleteById(id); }

}
