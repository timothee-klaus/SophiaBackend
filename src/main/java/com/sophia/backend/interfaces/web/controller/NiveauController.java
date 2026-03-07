package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.NiveauDTO;
import com.sophia.backend.application.service.NiveauService;
import com.sophia.backend.domain.model.Niveau;
import com.sophia.backend.infrastructure.persistence.mapper.NiveauMapper;
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

@Tag(name = "Niveaux", description = "Gestion des niveaux scolaires - 6eme, 5eme, CM2, etc.")
@RestController
@RequestMapping("/api/v1/niveaux")
@RequiredArgsConstructor
public class NiveauController {

    private final NiveauService service;
    private final NiveauMapper mapper;

    @Operation(summary = "Lister tous les niveaux", description = "Retourne tous les niveaux scolaires")
    @GetMapping
    public ResponseEntity<List<NiveauDTO>> getAll() {
        List<NiveauDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Recuperer un niveau par ID", description = "Retourne les details d'un niveau")
    @GetMapping("/{id}")
    public ResponseEntity<NiveauDTO> getById(@Parameter(description = "ID du niveau") @PathVariable Long id) {
        return service.findById(id)
                .map(n -> ResponseEntity.ok(mapper.toDto(n)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Niveaux par cycle", description = "Liste tous les niveaux d'un cycle (ex: tous les niveaux du College)")
    @GetMapping("/cycle/{cycleId}")
    public ResponseEntity<List<NiveauDTO>> getByCycleId(@Parameter(description = "ID du cycle") @PathVariable Long cycleId) {
        List<NiveauDTO> dtos = service.findByCycleId(cycleId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Niveaux par etablissement", description = "Liste tous les niveaux proposes par un etablissement")
    @GetMapping("/etablissement/{etablissementId}")
    public ResponseEntity<List<NiveauDTO>> getByEtablissementId(@Parameter(description = "ID de l'etablissement") @PathVariable Long etablissementId) {
        List<NiveauDTO> dtos = service.findByEtablissementId(etablissementId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<NiveauDTO> create(@RequestBody NiveauDTO dto) {
        Niveau niveau = mapper.toDomain(dto);
        Niveau saved = service.create(niveau);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NiveauDTO> update(@PathVariable Long id, @RequestBody NiveauDTO dto) {
        Niveau niveau = mapper.toDomain(dto);
        Niveau updated = service.update(niveau);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/configurer")
    public ResponseEntity<List<NiveauDTO>> configurerNiveauxEtablissement(
            @RequestParam Long etablissementId,
            @RequestParam Long cycleId) {
        List<NiveauDTO> dtos = service.configurerNiveauxEtablissement(etablissementId, cycleId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}


