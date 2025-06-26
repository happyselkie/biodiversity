package org.example.biodiversity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.biodiversity.dto.SpecieResponseDto;
import org.example.biodiversity.enums.Category;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Specie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String commonName;
    private String scientificName;

    private Category category;


    public SpecieResponseDto entityToDto() {
        return SpecieResponseDto.builder()
                .commonName(getCommonName())
                .scientificName(getScientificName())
                .category(getCategory().toString())
                .build();
    }
}
