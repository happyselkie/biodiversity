package org.example.biodiversity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class GBIFSpecieDto {
    private Long usageKey;
    private String scientificName;
    private String canonicalName;
    private String order;
    private String family;
    private String genus;
    private String species;
}
