package org.example.biodiversity.controller;

import jakarta.validation.Valid;
import org.example.biodiversity.dto.SpecieReceiveDto;
import org.example.biodiversity.dto.SpecieResponseDto;
import org.example.biodiversity.service.SpecieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("species")
public class SpecieController {

    private final SpecieService specieService;

    public SpecieController(SpecieService specieService) {
        this.specieService = specieService;
    }

    @GetMapping
    public ResponseEntity<List<SpecieResponseDto>> getAll() { return ResponseEntity.ok(specieService.get()); }

    @PostMapping
    public ResponseEntity<SpecieResponseDto> create(@Valid @RequestBody SpecieReceiveDto specie) { return ResponseEntity.status(HttpStatus.CREATED).body(specieService.create(specie)); }

    @GetMapping("/{id}")
    public ResponseEntity<SpecieResponseDto> getById(@PathVariable Long id) { return ResponseEntity.ok(specieService.get(id)); }
}
