package org.example.biodiversity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.biodiversity.dto.TravelLogResponseDto;
import org.example.biodiversity.enums.TravelMode;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TravelLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double distanceKm;
    private Double estimatedCo2Kg;

    private TravelMode mode;

    @ManyToOne
    @JoinColumn(name = "observationId")
    private Observation observation;

    public TravelLogResponseDto entityToDto() {

        return TravelLogResponseDto.builder()
                .distanceKm(getDistanceKm())
                .estimatedCo2Kg(estimatedCo2Kg)
                .mode(getMode().toString())
                .observation(getObservation().getName())
                .build();
    }


}
