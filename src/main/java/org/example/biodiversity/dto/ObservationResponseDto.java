package org.example.biodiversity.dto;


import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.biodiversity.entity.Specie;

import java.time.LocalDate;

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

    private Specie specie;

    private String geoJson;
}
