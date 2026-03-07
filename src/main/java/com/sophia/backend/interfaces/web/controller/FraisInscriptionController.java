package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.FraisInscriptionDTO;
import com.sophia.backend.application.service.FraisInscriptionService;
import com.sophia.backend.domain.model.FraisInscription;
import com.sophia.backend.infrastructure.persistence.mapper.FraisInscriptionMapper;
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

@Tag(name = "Frais Inscription", description = "Gestion des frais d'inscription - Parametrage par cycle et annee")
@RestController
@RequestMapping("/api/v1/frais-inscription")
@RequiredArgsConstructor
public class FraisInscriptionController {

    private final FraisInscriptionService service;
    private final FraisInscriptionMapper mapper;

    @Operation(summary = "Lister tous les frais d'inscription", description = "Retourne tous les frais d'inscription parametres")
    @GetMapping
    public ResponseEntity<List<FraisInscriptionDTO>> getAll() {
        List<FraisInscriptionDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Recuperer un frais d'inscription par ID", description = "Retourne les details d'un frais d'inscription")
    @GetMapping("/{id}")
    public ResponseEntity<FraisInscriptionDTO> getById(@Parameter(description = "ID des frais") @PathVariable Long id) {
        return service.findById(id)
                .map(f -> ResponseEntity.ok(mapper.toDto(f)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Frais d'inscription par cycle", description = "Liste les frais d'inscription pour un cycle (ex: College)")
    @GetMapping("/cycle/{cycleId}")
    public ResponseEntity<List<FraisInscriptionDTO>> getByCycleId(@Parameter(description = "ID du cycle") @PathVariable Long cycleId) {
        List<FraisInscriptionDTO> dtos = service.findByCycleId(cycleId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Frais d'inscription par annee scolaire", description = "Liste les frais d'inscription pour une annee")
    @GetMapping("/annee-scolaire/{anneeScolaireId}")
    public ResponseEntity<List<FraisInscriptionDTO>> getByAnneeScolaireId(@Parameter(description = "ID de l'annee") @PathVariable Long anneeScolaireId) {
        List<FraisInscriptionDTO> dtos = service.findByAnneeScolaireId(anneeScolaireId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<FraisInscriptionDTO> create(@RequestBody FraisInscriptionDTO dto) {
        FraisInscription frais = mapper.toDomain(dto);
        FraisInscription saved = service.create(frais);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @GetMapping("/cycle/{cycleId}/annee/{anneeScolaireId}")
    public ResponseEntity<FraisInscriptionDTO> obtenirFraisInscriptionPourCycle(
            @PathVariable Long cycleId,
            @PathVariable Long anneeScolaireId) {
        return service.obtenirFraisInscriptionPourCycle(cycleId, anneeScolaireId)
                .map(f -> ResponseEntity.ok(mapper.toDto(f)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FraisInscriptionDTO> update(@PathVariable Long id, @RequestBody FraisInscriptionDTO dto) {
        FraisInscription frais = mapper.toDomain(dto);
        FraisInscription updated = service.update(frais);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}


