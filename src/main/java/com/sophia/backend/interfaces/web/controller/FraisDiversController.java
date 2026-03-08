package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.FraisDiversDTO;
import com.sophia.backend.application.service.FraisDiversService;
import com.sophia.backend.domain.model.FraisDivers;
import com.sophia.backend.infrastructure.persistence.mapper.FraisDiversMapper;
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

@Tag(name = "Frais Divers", description = "Gestion des frais divers - Frais d'examen, activites, etc.")
@RestController
@RequestMapping("/api/v1/frais-divers")
@RequiredArgsConstructor
public class FraisDiversController {

    private final FraisDiversService service;
    private final FraisDiversMapper mapper;

    @Operation(summary = "Créer frais divers", description = "Crée une configuration de frais divers (ex: 3000 F pour classes d'examen)")
    @PostMapping
    public ResponseEntity<FraisDiversDTO> create(@RequestBody FraisDiversDTO dto) {
        FraisDivers frais = mapper.toDomain(dto);
        FraisDivers saved = service.create(frais);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @Operation(summary = "Lister les frais divers", description = "Retourne la liste de tous les frais divers")
    @GetMapping
    public ResponseEntity<List<FraisDiversDTO>> getAll() {
        List<FraisDiversDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Frais divers par année", description = "Retourne les frais divers pour une année scolaire spécifique")
    @GetMapping("/annee/{anneId}")
    public ResponseEntity<List<FraisDiversDTO>> getByAnneeScolaire(@PathVariable Long anneId) {
        List<FraisDiversDTO> dtos = service.findByAnneeScolaireId(anneId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Récupérer frais divers", description = "Retourne les détails d'une configuration de frais divers")
    @GetMapping("/{id}")
    public ResponseEntity<FraisDiversDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(f -> ResponseEntity.ok(mapper.toDto(f)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Modifier frais divers", description = "Modifie une configuration de frais divers")
    @PutMapping("/{id}")
    public ResponseEntity<FraisDiversDTO> update(@PathVariable Long id, @RequestBody FraisDiversDTO dto) {
        FraisDivers frais = mapper.toDomain(dto);
        FraisDivers updated = service.update(frais);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @Operation(summary = "Supprimer frais divers", description = "Supprime une configuration de frais divers")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
