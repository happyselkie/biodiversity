package org.example.biodiversity.controller;

import jakarta.validation.Valid;
import org.example.biodiversity.dto.ObservationReceiveDto;
import org.example.biodiversity.dto.ObservationResponseDto;
import org.example.biodiversity.service.ObservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("observations")
public class ObservationController {

    private final ObservationService observationService;

    public ObservationController(ObservationService observationService) {this.observationService = observationService;}

    @GetMapping
    public ResponseEntity<List<ObservationResponseDto>> getAll() { return ResponseEntity.ok(observationService.get()); }

    @GetMapping("/by-location/{location}")
    public ResponseEntity<List<ObservationResponseDto>> getByLocation(@PathVariable String location) { return ResponseEntity.ok(observationService.getByLocation(location)); }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<List<ObservationResponseDto>> getByName(@PathVariable String name) { return ResponseEntity.ok(observationService.getByName(name)); }

        @GetMapping("/by-date/{date}")
    public ResponseEntity<List<ObservationResponseDto>> getByDate(@PathVariable String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate;
        try{
            localDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(observationService.getByDate(localDate));
    }

    @GetMapping("/by-specie/{specieId}")
    public ResponseEntity<List<ObservationResponseDto>> getBySpecie(@PathVariable Long specieId) { return ResponseEntity.ok(observationService.getBySpecieId(specieId)); }

    @PostMapping
    public ResponseEntity<ObservationResponseDto> create(@Valid @RequestBody ObservationReceiveDto observation) { return ResponseEntity.status(HttpStatus.CREATED).body(observationService.create(observation)); }
}
