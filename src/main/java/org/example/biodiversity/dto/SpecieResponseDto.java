package org.example.biodiversity.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SpecieResponseDto {
    private String commonName;
    private String scientificName;

    private String category;
}
