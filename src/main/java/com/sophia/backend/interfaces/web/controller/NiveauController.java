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

    @Operation(summary = "Créer un niveau", description = "Crée une nouvelle classe/niveau (ex: 6ème, CM2, Jardin d'enfants)")
    @ApiResponse(responseCode = "201", description = "Niveau créé avec succès")
    @PostMapping
    public ResponseEntity<NiveauDTO> create(@RequestBody NiveauDTO dto) {
        Niveau niveau = mapper.toDomain(dto);
        Niveau saved = service.create(niveau);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @Operation(summary = "Lister les niveaux", description = "Retourne la liste de tous les niveaux")
    @GetMapping
    public ResponseEntity<List<NiveauDTO>> getAll() {
        List<NiveauDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Lister niveaux d'un cycle", description = "Retourne les niveaux associés à un cycle spécifique")
    @GetMapping("/cycle/{cycleId}")
    public ResponseEntity<List<NiveauDTO>> getByCycleId(@Parameter(description = "ID du cycle") @PathVariable Long cycleId) {
        List<NiveauDTO> dtos = service.findByCycleId(cycleId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Lister niveaux d'un établissement", description = "Retourne les niveaux d'un établissement spécifique")
    @GetMapping("/etablissement/{etabId}")
    public ResponseEntity<List<NiveauDTO>> getByEtablissementId(@Parameter(description = "ID de l'etablissement") @PathVariable Long etabId) {
        List<NiveauDTO> dtos = service.findByEtablissementId(etabId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Récupérer un niveau", description = "Retourne les détails d'un niveau spécifique")
    @GetMapping("/{id}")
    public ResponseEntity<NiveauDTO> getById(@Parameter(description = "ID du niveau") @PathVariable Long id) {
        return service.findById(id)
                .map(n -> ResponseEntity.ok(mapper.toDto(n)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Modifier un niveau", description = "Modifie les informations d'un niveau")
    @PutMapping("/{id}")
    public ResponseEntity<NiveauDTO> update(@PathVariable Long id, @RequestBody NiveauDTO dto) {
        Niveau niveau = mapper.toDomain(dto);
        Niveau updated = service.update(niveau);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @Operation(summary = "Supprimer un niveau", description = "Supprime un niveau du système")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
