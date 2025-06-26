package org.example.biodiversity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.biodiversity.dto.observation.ObservationResponseDto;
import org.example.biodiversity.service.GBIFSpecieService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
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

    private Long specieId;

    public ObservationResponseDto entityToDto() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return ObservationResponseDto.builder()
                .name(getName())
                .location(getLocation())
                .longitude(getLongitude())
                .latitude(getLatitude())
                .observationDate(getObservationDate().format(formatter))
                .comment(getComment())
                .specieId(getSpecieId())
                .geoJson("http://geojson.io/#data=data:application/json," + URLEncoder.encode(toGeoJson().toString()))
                .build();
    }

    public JSONObject toGeoJson(){
        JSONObject featureCollection = new JSONObject();
        try {
            featureCollection.put("type", "featureCollection");
            JSONArray featureList = new JSONArray();
            JSONObject point = new JSONObject();
            point.put("type", "Point");
            JSONArray coord = new JSONArray("["+getLongitude()+","+getLatitude()+"]");
            point.put("coordinates", coord);
            JSONObject feature = new JSONObject();
            feature.put("geometry", point);
            featureList.put(feature);
            featureCollection.put("features", featureList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return featureCollection;
    }
}
