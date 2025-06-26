package org.example.biodiversity.dto;


import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.biodiversity.entity.Observation;
import org.example.biodiversity.enums.TravelMode;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TravelLogResponseDto {
    private Double distanceKm;
    private Double estimatedCo2Kg;

    private String mode;
    private String observation;
}
