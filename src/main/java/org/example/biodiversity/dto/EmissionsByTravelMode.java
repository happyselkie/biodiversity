package org.example.biodiversity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.biodiversity.enums.TravelMode;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmissionsByTravelMode {
    private TravelMode travelMode;
    private Double emission;
}
