package org.example.biodiversity.service;

import org.example.biodiversity.dto.ObservationReceiveDto;
import org.example.biodiversity.dto.ObservationResponseDto;
import org.example.biodiversity.entity.Observation;
import org.example.biodiversity.entity.Specie;
import org.example.biodiversity.exception.NotFoundException;
import org.example.biodiversity.repository.ObservationRepository;
import org.example.biodiversity.repository.SpecieRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ObservationService {

    private final ObservationRepository observationRepository;
    private final SpecieRepository specieRepository;

    public ObservationService(ObservationRepository observationRepository, SpecieRepository specieRepository) {
        this.observationRepository = observationRepository;
        this.specieRepository = specieRepository;
    }

    public ObservationResponseDto create(ObservationReceiveDto observationReceiveDto){ return observationRepository.save(observationReceiveDto.dtoToEntity(specieRepository)).entityToDto(); }

    public ObservationResponseDto get(Long id){ return observationRepository.findById(id).orElseThrow(NotFoundException::new).entityToDto(); }

    public List<ObservationResponseDto> get(){
        return observationRepository.findAll().stream().map(Observation::entityToDto).toList();
    }

    public List<ObservationResponseDto> getByName(String name){
        return observationRepository.findAll().stream().filter(o -> o.getName().contains(name)).map(Observation::entityToDto).toList();
    }

    public List<ObservationResponseDto> getByLocation(String location){
        return observationRepository.findAll().stream().filter(o -> o.getLocation().contains(location)).map(Observation::entityToDto).toList();
    }

    public List<ObservationResponseDto> getByDate(LocalDate date){
        return observationRepository.findAll().stream().filter(o -> o.getObservationDate().equals(date)).map(Observation::entityToDto).toList();
    }

    public List<ObservationResponseDto> getBySpecieId(Long specieId){
        return observationRepository.findAll().stream().filter(o -> o.getSpecie().getId().equals(specieId)).map(Observation::entityToDto).toList();
    }

    public ObservationResponseDto update(Long id, ObservationReceiveDto ObservationReceiveDto){
        Observation observationToUpdate = observationRepository.findById(id).orElseThrow(NotFoundException::new);
        Observation observationGet = ObservationReceiveDto.dtoToEntity(specieRepository);
        observationToUpdate.setName(observationGet.getName());
        observationToUpdate.setLocation(observationGet.getLocation());
        observationToUpdate.setLongitude(observationGet.getLongitude());
        observationToUpdate.setLatitude(observationGet.getLatitude());
        observationToUpdate.setObservationDate(observationGet.getObservationDate());
        observationToUpdate.setComment(observationGet.getComment());
        observationToUpdate.setSpecie(observationGet.getSpecie());
        return observationRepository.save(observationToUpdate).entityToDto();
    }

    public void delete(Long id){ observationRepository.deleteById(id); }
}
