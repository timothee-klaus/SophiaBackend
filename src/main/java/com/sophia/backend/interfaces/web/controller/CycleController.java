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

    @Operation(summary = "Créer un cycle", description = "Crée un nouveau cycle scolaire (ex: Préscolaire, Primaire, Collège, Lycée)")
    @ApiResponse(responseCode = "201", description = "Cycle créé avec succès")
    @PostMapping
    public ResponseEntity<CycleDTO> create(@RequestBody CycleDTO dto) {
        Cycle cycle = mapper.toDomain(dto);
        Cycle saved = service.create(cycle);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @Operation(summary = "Lister les cycles", description = "Retourne la liste de tous les cycles disponibles")
    @ApiResponse(responseCode = "200", description = "Liste des cycles récupérée")
    @GetMapping
    public ResponseEntity<List<CycleDTO>> getAll() {
        List<CycleDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Récupérer un cycle", description = "Retourne les détails d'un cycle spécifique")
    @GetMapping("/{id}")
    public ResponseEntity<CycleDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(c -> ResponseEntity.ok(mapper.toDto(c)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Modifier un cycle", description = "Modifie les informations d'un cycle")
    @PutMapping("/{id}")
    public ResponseEntity<CycleDTO> update(@PathVariable Long id, @RequestBody CycleDTO dto) {
        Cycle cycle = mapper.toDomain(dto);
        Cycle updated = service.update(cycle);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @Operation(summary = "Supprimer un cycle", description = "Supprime un cycle du système")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
