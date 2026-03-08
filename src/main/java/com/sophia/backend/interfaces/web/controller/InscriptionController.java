package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.*;
import com.sophia.backend.application.service.InscriptionService;
import com.sophia.backend.domain.model.Inscription;
import com.sophia.backend.infrastructure.persistence.mapper.InscriptionMapper;
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

@Tag(name = "Inscriptions", description = "Gestion des inscriptions scolaires - Depot de dossier, validation des pieces, suivi des statuts")
@RestController
@RequestMapping("/api/v1/inscriptions")
@RequiredArgsConstructor
public class InscriptionController {

    private final InscriptionService service;
    private final InscriptionMapper mapper;

    @Operation(summary = "Créer une inscription", description = "Enregistre le dépôt d'un dossier d'inscription")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Inscription créée avec succès"),
        @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    public ResponseEntity<InscriptionDTO> create(@RequestBody InscriptionDTO dto) {
        Inscription inscription = mapper.toDomain(dto);
        Inscription saved = service.enregistrerDossierInscription(inscription);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @Operation(summary = "Lister les inscriptions", description = "Retourne la liste de toutes les inscriptions")
    @ApiResponse(responseCode = "200", description = "Liste des inscriptions récupérée")
    @GetMapping
    public ResponseEntity<List<InscriptionDTO>> getAll() {
        List<InscriptionDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Récupérer une inscription", description = "Retourne les détails d'une inscription spécifique")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Inscription trouvée"),
        @ApiResponse(responseCode = "404", description = "Inscription non trouvée")
    })
    @GetMapping("/{id}")
    public ResponseEntity<InscriptionDTO> getById(@Parameter(description = "ID de l'inscription") @PathVariable Long id) {
        return service.findById(id)
                .map(i -> ResponseEntity.ok(mapper.toDto(i)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Valider les pièces", description = "Valide les pièces fournies pour l'inscription")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pièces validées avec succès"),
        @ApiResponse(responseCode = "404", description = "Inscription non trouvée")
    })
    @PutMapping("/{id}/valider-pieces")
    public ResponseEntity<InscriptionDTO> validerPieces(@Parameter(description = "ID de l'inscription") @PathVariable Long id) {
        try {
            Inscription validated = service.validerPiecesInscription(id);
            return ResponseEntity.ok(mapper.toDto(validated));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Enregistrer paiement inscription", description = "Enregistre le paiement des frais d'inscription (5000, 10000 ou 3000 F)")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Paiement enregistré"),
        @ApiResponse(responseCode = "404", description = "Inscription non trouvée")
    })
    @PostMapping("/{id}/paiement")
    public ResponseEntity<InscriptionDTO> enregistrerPaiement(@Parameter(description = "ID de l'inscription") @PathVariable Long id, @RequestBody PaiementDTO paiement) {
        try {
            Inscription updated = service.enregistrerPaiementInscription(id);
            return ResponseEntity.ok(mapper.toDto(updated));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Générer reçu inscription", description = "Génère un reçu pour les frais d'inscription")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Reçu généré"),
        @ApiResponse(responseCode = "404", description = "Inscription non trouvée")
    })
    @PostMapping("/{id}/generer-recu")
    public ResponseEntity<RecuDTO> genererRecu(@Parameter(description = "ID de l'inscription") @PathVariable Long id) {
        try {
            RecuDTO recu = new RecuDTO();
            return ResponseEntity.ok(recu);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}


