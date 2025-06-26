package org.example.biodiversity.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.biodiversity.entity.TravelLog;
import org.example.biodiversity.enums.TravelMode;
import org.example.biodiversity.exception.NotFoundException;
import org.example.biodiversity.repository.ObservationRepository;
import org.example.biodiversity.util.AllowedValues;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TravelLogReceiveDto {

    @NotNull (message = "la distance ne doit pas être nulle")
    @Positive (message = "La distance doit être positive")
    private Double distanceKm;

    @NotNull(message = "Le mode de transport doit être valide (WALKING, BIKE, CAR, BUS, TRAIN, PLANE)")
    @NotBlank(message = "Le mode de transport doit être valide (WALKING, BIKE, CAR, BUS, TRAIN, PLANE)")
    @AllowedValues(values = {"WALKING", "BIKE", "CAR", "BUS", "TRAIN", "PLANE"}, message = "Le mode de transport doit être valide (WALKING, BIKE, CAR, BUS, TRAIN, PLANE)")
    private String mode;

    @NotNull(message = "Doit être lié à une observation")
    private Long observationId;


    public Double calcCo2(){
        switch (TravelMode.valueOf(getMode())){
            case WALKING, BIKE:
                return 0.0;
            case CAR :
                return getDistanceKm() * 0.22;
            case BUS:
                return getDistanceKm() * 0.11;
            case TRAIN:
                return getDistanceKm() * 0.03;
            case PLANE:
                return getDistanceKm() * 0.259;
            default:
                throw new IllegalArgumentException("Invalid travel mode: " + getMode());
        }
    }

    public TravelLog dtoToEntity(ObservationRepository observationRepository){

        return TravelLog.builder()
                .distanceKm(getDistanceKm())
                .estimatedCo2Kg(calcCo2())
                .mode(TravelMode.valueOf(getMode()))
                .observation(observationRepository.findById(getObservationId()).orElseThrow(NotFoundException::new))
                .build();
    }
}
