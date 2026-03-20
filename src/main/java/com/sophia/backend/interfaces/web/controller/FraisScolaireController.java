package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.FraisScolaireDTO;
import com.sophia.backend.application.service.FraisScolaireService;
import com.sophia.backend.domain.model.FraisScolaire;
import com.sophia.backend.infrastructure.persistence.mapper.FraisScolaireMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Tag(name = "Frais Scolaires", description = "Gestion des frais de scolarite - Montant total annuel par niveau")
@RestController
@RequestMapping("/api/v1/frais-scolaires")
@RequiredArgsConstructor
public class FraisScolaireController {

    private final FraisScolaireService service;
    private final FraisScolaireMapper mapper;

    @Operation(summary = "Créer frais scolaires", description = "Crée la configuration des frais scolaires pour un niveau et une année donnés")
    @PostMapping
    public ResponseEntity<FraisScolaireDTO> create(@RequestBody FraisScolaireDTO dto) {
        dto.setUuid(null);
        FraisScolaire frais = mapper.toDomain(dto);
        FraisScolaire saved = service.create(frais);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @Operation(summary = "Lister les frais scolaires", description = "Retourne la liste de tous les frais scolaires")
    @GetMapping
    public ResponseEntity<List<FraisScolaireDTO>> getAll() {
        List<FraisScolaireDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Frais scolaires par année", description = "Retourne les frais scolaires pour une année scolaire spécifique")
    @GetMapping("/annee/{anneUuid}")
    public ResponseEntity<List<FraisScolaireDTO>> getByAnneeScolaire(@PathVariable UUID anneUuid) {
        List<FraisScolaireDTO> dtos = service.findByAnneeScolaireUuid(anneUuid).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Récupérer frais scolaires", description = "Retourne les détails d'une configuration de frais scolaires")
    @GetMapping("/{uuid}")
    public ResponseEntity<FraisScolaireDTO> getByUuid(@PathVariable UUID uuid) {
        return service.findByUuid(uuid)
                .map(f -> ResponseEntity.ok(mapper.toDto(f)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Modifier frais scolaires", description = "Modifie la configuration des frais scolaires")
    @PutMapping("/{uuid}")
    public ResponseEntity<FraisScolaireDTO> update(@PathVariable UUID uuid, @RequestBody FraisScolaireDTO dto) {
        FraisScolaire frais = mapper.toDomain(dto);
        FraisScolaire updated = service.update(uuid, frais);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @Operation(summary = "Supprimer frais scolaires", description = "Supprime une configuration de frais scolaires")
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        service.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}
