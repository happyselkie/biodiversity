package org.example.biodiversity.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.biodiversity.entity.Specie;
import org.example.biodiversity.enums.Category;
import org.example.biodiversity.util.AllowedValues;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SpecieReceiveDto {

    @Size(min = 3, max = 25, message = "Le nom doit être compris entre 3 et 25 caractères")
    private String commonName;

    @Size(min = 3, max = 25, message = "Le nom scientifique doit être compris entre 3 et 25 caractères")
    private String scientificName;

    @NotBlank(message = "La catégorie ne pas être vide")
    @NotNull(message = "La catégorie ne pas être vide")
    @AllowedValues(values = {"BIRD", "MAMMAL", "INSECT", "PLANT", "OTHER"}, message = "La catégorie doit être valide (BIRD, MAMMAL, INSECT, PLANT, OTHER)")
    private String category;


    public Specie dtoToEntity(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return Specie.builder()
                .commonName(getCommonName())
                .scientificName(getScientificName())
                .category(Category.valueOf(getCategory()))
                .build();

    }
}
