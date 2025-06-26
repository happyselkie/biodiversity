package org.example.biodiversity.controller;

import jakarta.validation.Valid;
import org.example.biodiversity.dto.StatDto;
import org.example.biodiversity.dto.TravelLogReceiveDto;
import org.example.biodiversity.dto.TravelLogResponseDto;
import org.example.biodiversity.service.TravelLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("travel-logs")
public class TravelLogController {

    private final TravelLogService travelLogService;

    public TravelLogController(TravelLogService travelLogService) {
        this.travelLogService = travelLogService;
    }

    @GetMapping
    public ResponseEntity<List<TravelLogResponseDto>> getAll() { return ResponseEntity.ok(travelLogService.get()); }

    @GetMapping("/stats/{idObservation}")
    public ResponseEntity<StatDto> getStatsByObservation(@PathVariable Long idObservation ) {
        return ResponseEntity.ok(travelLogService.getStatByObservationId(idObservation));
    }

    @PostMapping
    public ResponseEntity<TravelLogResponseDto> create(@Valid @RequestBody TravelLogReceiveDto travelLog) { return ResponseEntity.status(HttpStatus.CREATED).body(travelLogService.create(travelLog)); }
}
