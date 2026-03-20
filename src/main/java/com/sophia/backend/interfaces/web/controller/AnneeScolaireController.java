package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.AnneeScolaireDTO;
import com.sophia.backend.application.dto.CreateAnneeScolaireDTO;
import com.sophia.backend.application.service.AnneeScolaireService;
import com.sophia.backend.domain.model.AnneeScolaire;
import com.sophia.backend.infrastructure.persistence.mapper.AnneeScolaireMapper;
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

@Tag(name = "Annees Scolaires", description = "Gestion des annees scolaires - Ouverture, cloture, activation")
@RestController
@RequestMapping("/api/v1/annees-scolaires")
@RequiredArgsConstructor
public class AnneeScolaireController {

    private final AnneeScolaireService service;
    private final AnneeScolaireMapper mapper;

    @Operation(
        summary = "Créer année scolaire",
        description = "Crée une nouvelle année scolaire (ex: 2024-2025, 2025-2026).\n\n" +
                     "L'attribut 'estActive' est calculé automatiquement par le système\n" +
                     "basé sur la date actuelle vs dateDebut et dateFin.\n" +
                     "Le UID est généré automatiquement.\n\n" +
                     "NE PAS ENVOYER: uid, estActive"
    )
    @ApiResponse(responseCode = "201", description = "Année scolaire créée avec succès")
    @PostMapping
    public ResponseEntity<AnneeScolaireDTO> create(@RequestBody CreateAnneeScolaireDTO dto) {
        AnneeScolaire annee = new AnneeScolaire();
        annee.setLibelle(dto.getLibelle());
        annee.setDateDebut(dto.getDateDebut());
        annee.setDateFin(dto.getDateFin());
        
        AnneeScolaire saved = service.create(annee);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @Operation(summary = "Lister les années scolaires", description = "Retourne la liste de toutes les années scolaires")
    @ApiResponse(responseCode = "200", description = "Liste des années scolaires récupérée")
    @GetMapping
    public ResponseEntity<List<AnneeScolaireDTO>> getAll() {
        List<AnneeScolaireDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Récupérer une année scolaire", description = "Retourne les détails d'une année scolaire spécifique par son UID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Année scolaire trouvée"),
        @ApiResponse(responseCode = "404", description = "Année scolaire non trouvée")
    })
    @GetMapping("/{uid}")
    public ResponseEntity<AnneeScolaireDTO> getByUid(@Parameter(description = "UID de l'année scolaire (UUID)") @PathVariable UUID uid) {
        return service.findByUid(uid)
                .map(a -> ResponseEntity.ok(mapper.toDto(a)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Année scolaire active", description = "Retourne l'année scolaire actuellement active (déterminée par les dates du système)")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Année scolaire active trouvée"),
        @ApiResponse(responseCode = "404", description = "Aucune année scolaire active")
    })
    @GetMapping("/active")
    public ResponseEntity<AnneeScolaireDTO> getActive() {
        return service.obtenirAnneeScolaireActive()
                .map(a -> ResponseEntity.ok(mapper.toDto(a)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Modifier une année scolaire", description = "Modifie les dates d'une année scolaire.\n\nNOTE: estActive est déterminé automatiquement par le système.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Année scolaire modifiée avec succès"),
        @ApiResponse(responseCode = "404", description = "Année scolaire non trouvée")
    })
    @PutMapping("/{uid}")
    public ResponseEntity<AnneeScolaireDTO> update(
            @Parameter(description = "UID de l'année scolaire") @PathVariable UUID uid,
            @RequestBody AnneeScolaireDTO dto) {
        try {
            AnneeScolaire annee = mapper.toDomain(dto);
            AnneeScolaire updated = service.update(uid, annee);
            return ResponseEntity.ok(mapper.toDto(updated));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Supprimer une année scolaire", description = "Supprime une année scolaire")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Année scolaire supprimée avec succès"),
        @ApiResponse(responseCode = "404", description = "Année scolaire non trouvée")
    })
    @DeleteMapping("/{uid}")
    public ResponseEntity<Void> delete(@Parameter(description = "UID de l'année scolaire") @PathVariable UUID uid) {
        try {
            service.deleteByUid(uid);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
