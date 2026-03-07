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
import java.util.stream.Collectors;

@Tag(name = "Frais Scolaires", description = "Gestion des frais de scolarite - Montant total annuel par niveau")
@RestController
@RequestMapping("/api/v1/frais-scolaires")
@RequiredArgsConstructor
public class FraisScolaireController {

    private final FraisScolaireService service;
    private final FraisScolaireMapper mapper;

    @Operation(summary = "Lister tous les frais scolaires", description = "Retourne tous les frais scolaires definis")
    @GetMapping
    public ResponseEntity<List<FraisScolaireDTO>> getAll() {
        List<FraisScolaireDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Recuperer des frais scolaires par ID", description = "Retourne les details d'un frais scolaire")
    @GetMapping("/{id}")
    public ResponseEntity<FraisScolaireDTO> getById(@Parameter(description = "ID des frais") @PathVariable Long id) {
        return service.findById(id)
                .map(f -> ResponseEntity.ok(mapper.toDto(f)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Frais scolaires par niveau", description = "Liste les frais scolaires pour un niveau specifique")
    @GetMapping("/niveau/{niveauId}")
    public ResponseEntity<List<FraisScolaireDTO>> getByNiveauId(@Parameter(description = "ID du niveau") @PathVariable Long niveauId) {
        List<FraisScolaireDTO> dtos = service.findByNiveauId(niveauId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Frais scolaires par annee", description = "Liste les frais scolaires pour une annee scolaire")
    @GetMapping("/annee-scolaire/{anneeScolaireId}")
    public ResponseEntity<List<FraisScolaireDTO>> getByAnneeScolaireId(@Parameter(description = "ID de l'annee scolaire") @PathVariable Long anneeScolaireId) {
        List<FraisScolaireDTO> dtos = service.findByAnneeScolaireId(anneeScolaireId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<FraisScolaireDTO> create(@RequestBody FraisScolaireDTO dto) {
        FraisScolaire frais = mapper.toDomain(dto);
        FraisScolaire saved = service.create(frais);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @PostMapping("/definir")
    public ResponseEntity<FraisScolaireDTO> definir(
            @RequestParam Long niveauId,
            @RequestParam Long anneeScolaireId,
            @RequestBody FraisScolaireDTO dto) {
        FraisScolaire frais = mapper.toDomain(dto);
        FraisScolaire saved = service.definirFraisScolaire(niveauId, anneeScolaireId, frais);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @GetMapping("/niveau/{niveauId}/annee/{anneeScolaireId}")
    public ResponseEntity<FraisScolaireDTO> obtenirFraisPourNiveauEtAnnee(
            @PathVariable Long niveauId,
            @PathVariable Long anneeScolaireId) {
        return service.obtenirFraisPourNiveauEtAnnee(niveauId, anneeScolaireId)
                .map(f -> ResponseEntity.ok(mapper.toDto(f)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FraisScolaireDTO> update(@PathVariable Long id, @RequestBody FraisScolaireDTO dto) {
        FraisScolaire frais = mapper.toDomain(dto);
        FraisScolaire updated = service.update(frais);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}


