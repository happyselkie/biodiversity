package org.example.biodiversity.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.biodiversity.entity.Observation;
import org.example.biodiversity.exception.NotFoundException;
import org.example.biodiversity.repository.SpecieRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ObservationReceiveDto {

    @Size(min = 3, max = 25, message = "Le nom doit être compris entre 3 et 25 caractères")
    private String name;

    @Size(min = 3, max = 25, message = "Le lieu doit être compris entre 3 et 25 caractères")
    private String location;

    @NotNull(message = "La longitude ne doit pas être vide")
    private Double longitude;

    @NotNull(message = "La latitude ne doit pas être vide")
    private Double latitude;

    @Pattern(regexp = "[0-9]{2}[-|\\/]{1}[0-9]{2}[-|\\/]{1}[0-9]{4}", message = "La date doit être au format dd-MM-yyyy")
    private String observationDate;

    private String comment;

    @NotNull(message = "Doit être lié à une espèce")
    private Long specieId;


    public Observation dtoToEntity(SpecieRepository specieRepository){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return Observation.builder()
                .name(getName())
                .location(getLocation())
                .longitude(getLongitude())
                .latitude(getLatitude())
                .observationDate(LocalDate.parse(getObservationDate(), formatter))
                .comment(getComment())
                .specie(specieRepository.findById(getSpecieId()).orElseThrow(NotFoundException::new))
                .build();

    }
}
