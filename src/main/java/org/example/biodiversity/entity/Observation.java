package org.example.biodiversity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.biodiversity.dto.ObservationResponseDto;
import org.example.biodiversity.dto.SpecieResponseDto;
import org.example.biodiversity.repository.SpecieRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Observation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;

    private Double longitude;
    private Double latitude;

    private LocalDate observationDate ;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "specieId")
    private Specie specie;

    public ObservationResponseDto entityToDto() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return ObservationResponseDto.builder()
                .name(getName())
                .location(getLocation())
                .longitude(getLongitude())
                .latitude(getLatitude())
                .observationDate(getObservationDate().format(formatter))
                .comment(getComment())
                .specie(getSpecie())
                .build();
    }
}
