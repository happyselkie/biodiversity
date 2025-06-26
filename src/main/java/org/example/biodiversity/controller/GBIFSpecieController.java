package org.example.biodiversity.controller;

import org.example.biodiversity.dto.GBIFSpecieDto;
import org.example.biodiversity.service.GBIFSpecieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("gbif-species")
public class GBIFSpecieController {

    private final GBIFSpecieService gbifSpecieService;

    public GBIFSpecieController(GBIFSpecieService gbifSpecieService) {this.gbifSpecieService = gbifSpecieService;}

    @GetMapping("/{specieId}")
    public ResponseEntity<GBIFSpecieDto> get(@PathVariable Long specieId) { return ResponseEntity.ok(gbifSpecieService.getById(specieId)); }

}
