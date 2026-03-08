package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.AnneeScolaireDTO;
import com.sophia.backend.application.service.AnneeScolaireService;
import com.sophia.backend.domain.model.AnneeScolaire;
import com.sophia.backend.infrastructure.persistence.mapper.AnneeScolaireMapper;
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

@Tag(name = "Annees Scolaires", description = "Gestion des annees scolaires - Ouverture, cloture, activation")
@RestController
@RequestMapping("/api/v1/annees-scolaires")
@RequiredArgsConstructor
public class AnneeScolaireController {

    private final AnneeScolaireService service;
    private final AnneeScolaireMapper mapper;

    @Operation(summary = "Créer année scolaire", description = "Crée une nouvelle année scolaire (ex: 2024-2025, 2025-2026)")
    @PostMapping
    public ResponseEntity<AnneeScolaireDTO> create(@RequestBody AnneeScolaireDTO dto) {
        AnneeScolaire annee = mapper.toDomain(dto);
        AnneeScolaire saved = service.create(annee);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @Operation(summary = "Lister les années scolaires", description = "Retourne la liste de toutes les années scolaires")
    @GetMapping
    public ResponseEntity<List<AnneeScolaireDTO>> getAll() {
        List<AnneeScolaireDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Récupérer une année scolaire", description = "Retourne les détails d'une année scolaire spécifique")
    @GetMapping("/{id}")
    public ResponseEntity<AnneeScolaireDTO> getById(@Parameter(description = "ID de l'annee scolaire") @PathVariable Long id) {
        return service.findById(id)
                .map(a -> ResponseEntity.ok(mapper.toDto(a)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Année scolaire active", description = "Retourne l'année scolaire actuellement active")
    @GetMapping("/active")
    public ResponseEntity<AnneeScolaireDTO> getActive() {
        return service.obtenirAnneeScolaireActive()
                .map(a -> ResponseEntity.ok(mapper.toDto(a)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Modifier une année scolaire", description = "Modifie une année scolaire (ouvrir/clôturer)")
    @PutMapping("/{id}")
    public ResponseEntity<AnneeScolaireDTO> update(@PathVariable Long id, @RequestBody AnneeScolaireDTO dto) {
        AnneeScolaire annee = mapper.toDomain(dto);
        AnneeScolaire updated = service.update(annee);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @Operation(summary = "Supprimer une année scolaire", description = "Supprime une année scolaire")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
