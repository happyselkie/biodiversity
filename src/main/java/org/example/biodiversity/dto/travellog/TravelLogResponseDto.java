package org.example.biodiversity.dto.travellog;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
