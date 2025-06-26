package org.example.biodiversity.service;

import org.example.biodiversity.dto.GBIFSpecieDto;
import org.example.biodiversity.dto.observation.ObservationReceiveDto;
import org.example.biodiversity.dto.observation.ObservationResponseDto;
import org.example.biodiversity.entity.Observation;
import org.example.biodiversity.exception.NotFoundException;
import org.example.biodiversity.repository.ObservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ObservationService {

    private final ObservationRepository observationRepository;
    private final GBIFSpecieService gbifSpecieService;

    public ObservationService(ObservationRepository observationRepository, GBIFSpecieService gbifSpecieService) {
        this.observationRepository = observationRepository;
        this.gbifSpecieService = gbifSpecieService;
    }

    public ObservationResponseDto create(ObservationReceiveDto observationReceiveDto){ return observationRepository.save(observationReceiveDto.dtoToEntity()).entityToDto(); }

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
        return observationRepository.findAll().stream().filter(o -> o.getSpecieId().equals(specieId)).map(Observation::entityToDto).toList();
    }

    public List<ObservationResponseDto> getBySpecieName(String specieName){
        GBIFSpecieDto specie = gbifSpecieService.getByName(specieName);
        return observationRepository.findAll().stream().filter(o -> o.getSpecieId().equals(specie.getUsageKey())).map(Observation::entityToDto).toList();
    }
}
