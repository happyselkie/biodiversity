package org.example.biodiversity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StatDto {
    private Double totalDistanceKm;
    private Double totalEmissionsKg;

    private List<EmissionsByTravelMode> byMode;
}
