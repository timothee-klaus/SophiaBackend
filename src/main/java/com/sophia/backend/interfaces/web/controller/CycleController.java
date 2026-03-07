package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.CycleDTO;
import com.sophia.backend.application.service.CycleService;
import com.sophia.backend.domain.model.Cycle;
import com.sophia.backend.infrastructure.persistence.mapper.CycleMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Cycles", description = "Gestion des cycles scolaires - Prescolaire, Primaire, College, Lycee")
@RestController
@RequestMapping("/api/v1/cycles")
@RequiredArgsConstructor
public class CycleController {

    private final CycleService service;
    private final CycleMapper mapper;

    @Operation(summary = "Lister tous les cycles", description = "Retourne tous les cycles scolaires disponibles")
    @GetMapping
    public ResponseEntity<List<CycleDTO>> getAll() {
        List<CycleDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Recuperer un cycle par ID", description = "Retourne les details d'un cycle")
    @GetMapping("/{id}")
    public ResponseEntity<CycleDTO> getById(@Parameter(description = "ID du cycle") @PathVariable Long id) {
        return service.findById(id)
                .map(c -> ResponseEntity.ok(mapper.toDto(c)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Creer un cycle", description = "Cree un nouveau cycle scolaire")
    @ApiResponse(responseCode = "201", description = "Cycle cree")
    @PostMapping
    public ResponseEntity<CycleDTO> create(@RequestBody CycleDTO dto) {
        Cycle cycle = mapper.toDomain(dto);
        Cycle saved = service.create(cycle);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @Operation(summary = "Modifier un cycle", description = "Met a jour un cycle existant")
    @PutMapping("/{id}")
    public ResponseEntity<CycleDTO> update(@Parameter(description = "ID du cycle") @PathVariable Long id, @RequestBody CycleDTO dto) {
        Cycle cycle = mapper.toDomain(dto);
        Cycle updated = service.update(cycle);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<CycleDTO>> obtenirCyclesDisponibles() {
        List<CycleDTO> dtos = service.obtenirCyclesDisponibles().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}


