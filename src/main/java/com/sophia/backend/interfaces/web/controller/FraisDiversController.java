package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.FraisDiversDTO;
import com.sophia.backend.application.service.FraisDiversService;
import com.sophia.backend.domain.model.FraisDivers;
import com.sophia.backend.infrastructure.persistence.mapper.FraisDiversMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Tag(name = "Frais Divers", description = "Gestion des frais divers - Frais d'examen, activites, etc.")
@RestController
@RequestMapping("/api/v1/frais-divers")
@RequiredArgsConstructor
public class FraisDiversController {

    private final FraisDiversService service;
    private final FraisDiversMapper mapper;

    @Operation(
        summary = "Créer frais divers",
        description = "Crée une configuration de frais divers. Le UID est généré automatiquement par le système."
    )
    @ApiResponse(responseCode = "201", description = "Frais divers créé avec succès")
    @PostMapping
    public ResponseEntity<FraisDiversDTO> create(@RequestBody FraisDiversDTO dto) {
        dto.setUuid(null);
        FraisDivers frais = mapper.toDomain(dto);
        FraisDivers saved = service.create(frais);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @Operation(summary = "Lister les frais divers", description = "Retourne la liste de tous les frais divers")
    @ApiResponse(responseCode = "200", description = "Liste des frais divers récupérée")
    @GetMapping
    public ResponseEntity<List<FraisDiversDTO>> getAll() {
        List<FraisDiversDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Frais divers par année", description = "Retourne les frais divers pour une année scolaire spécifique")
    @GetMapping("/annee/{anneUuid}")
    public ResponseEntity<List<FraisDiversDTO>> getByAnneeScolaire(@PathVariable UUID anneUuid) {
        List<FraisDiversDTO> dtos = service.findByAnneeScolaireUuid(anneUuid).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Récupérer frais divers", description = "Retourne les détails d'une configuration de frais divers par son UUID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Frais divers trouvé"),
        @ApiResponse(responseCode = "404", description = "Frais divers non trouvé")
    })
    @GetMapping("/{uuid}")
    public ResponseEntity<FraisDiversDTO> getByUuid(@Parameter(description = "UUID du frais divers") @PathVariable UUID uuid) {
        return service.findByUuid(uuid)
                .map(f -> ResponseEntity.ok(mapper.toDto(f)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Modifier frais divers", description = "Modifie une configuration de frais divers")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Frais divers modifié avec succès"),
        @ApiResponse(responseCode = "404", description = "Frais divers non trouvé")
    })
    @PutMapping("/{uuid}")
    public ResponseEntity<FraisDiversDTO> update(
            @Parameter(description = "UUID du frais divers") @PathVariable UUID uuid,
            @RequestBody FraisDiversDTO dto) {
        try {
            FraisDivers frais = mapper.toDomain(dto);
            FraisDivers updated = service.update(uuid, frais);
            return ResponseEntity.ok(mapper.toDto(updated));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Supprimer frais divers", description = "Supprime une configuration de frais divers")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Frais divers supprimé avec succès"),
        @ApiResponse(responseCode = "404", description = "Frais divers non trouvé")
    })
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@Parameter(description = "UUID du frais divers") @PathVariable UUID uuid) {
        try {
            service.deleteByUuid(uuid);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
