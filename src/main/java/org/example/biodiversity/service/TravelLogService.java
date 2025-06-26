package org.example.biodiversity.service;

import org.example.biodiversity.dto.*;
import org.example.biodiversity.dto.travellog.TravelLogReceiveDto;
import org.example.biodiversity.dto.travellog.TravelLogResponseDto;
import org.example.biodiversity.entity.Observation;
import org.example.biodiversity.entity.TravelLog;
import org.example.biodiversity.exception.NotFoundException;
import org.example.biodiversity.repository.ObservationRepository;
import org.example.biodiversity.repository.TravelLogRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TravelLogService {

    private final TravelLogRepository travelLogRepository;
    private final ObservationRepository observationRepository;

    public TravelLogService(TravelLogRepository travelLogRepository, ObservationRepository observationRepository) {
        this.travelLogRepository = travelLogRepository;
        this.observationRepository = observationRepository;
    }

    public TravelLogResponseDto create(TravelLogReceiveDto travelLogReceiveDto){ return travelLogRepository.save(travelLogReceiveDto.dtoToEntity(observationRepository)).entityToDto(); }

    public TravelLogResponseDto get(Long id){ return travelLogRepository.findById(id).orElseThrow(NotFoundException::new).entityToDto(); }

    public List<TravelLogResponseDto> get(){
        return travelLogRepository.findAll().stream().map(TravelLog::entityToDto).toList();
    }

    public List<TravelLogResponseDto> getByObservationId(Long observationId){
        return travelLogRepository.findAll().stream().filter(t -> t.getObservation().getId().equals(observationId)).map(TravelLog::entityToDto).toList();
    }

    public List<TravelLogResponseDto> getByUserName(String userName){
        List<Observation> observations = observationRepository.findByNameContaining(userName);
        List<TravelLogResponseDto> userTravels = new ArrayList<>();
        for (Observation observation : observations) {
            List<TravelLogResponseDto> travels = getByObservationId(observation.getId());
            for (TravelLogResponseDto travel : travels) {
                userTravels.add(travel);
            }
        }
        return userTravels;
    }

    public StatDto getStatByObservationId(Long observationId){
        Double totalDistanceKm = travelLogRepository.getTotalDistanceKmByObservationId(observationId);
        List<EmissionsByTravelMode> emissionsKg = travelLogRepository.getTotalCo2ByObservationId(observationId);
        Double totalEmissionsKg = 0.0;

        for (EmissionsByTravelMode emission : emissionsKg) {
            totalEmissionsKg = totalEmissionsKg + emission.getEmission();
        }

        return StatDto.builder().totalDistanceKm(totalDistanceKm).totalEmissionsKg(totalEmissionsKg).byMode(emissionsKg).build();
    }

    public TravelLogResponseDto update(Long id, TravelLogReceiveDto TravelLogReceiveDto){
        TravelLog travelLogToUpdate = travelLogRepository.findById(id).orElseThrow(NotFoundException::new);
        TravelLog travelLogGet = TravelLogReceiveDto.dtoToEntity(observationRepository);
        travelLogToUpdate.setDistanceKm(travelLogGet.getDistanceKm());
        travelLogToUpdate.setEstimatedCo2Kg(travelLogGet.getEstimatedCo2Kg());
        travelLogToUpdate.setMode(travelLogGet.getMode());
        travelLogToUpdate.setObservation(travelLogGet.getObservation());
        return travelLogRepository.save(travelLogToUpdate).entityToDto();
    }

    public void delete(Long id){ travelLogRepository.deleteById(id); }
}
