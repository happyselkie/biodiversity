package org.example.biodiversity.dto.observation;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.biodiversity.dto.GBIFSpecieDto;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ObservationResponseDto {
    private String name;
    private String location;

    private Double longitude;
    private Double latitude;

    private String observationDate;

    private String comment;

    private Long specieId;

    private String geoJson;
}
