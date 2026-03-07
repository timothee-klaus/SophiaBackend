package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.RecuDTO;
import com.sophia.backend.application.service.RecuService;
import com.sophia.backend.domain.model.Recu;
import com.sophia.backend.infrastructure.persistence.mapper.RecuMapper;
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

@Tag(name = "Recus", description = "Gestion des recus de paiement - Generation, demande et telechargement des recus PDF")
@RestController
@RequestMapping("/api/v1/recus")
@RequiredArgsConstructor
public class RecuController {

    private final RecuService service;
    private final RecuMapper mapper;

    @Operation(summary = "Lister tous les recus", description = "Retourne la liste de tous les recus generes")
    @ApiResponse(responseCode = "200", description = "Liste des recus")
    @GetMapping
    public ResponseEntity<List<RecuDTO>> getAll() {
        List<RecuDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Recuperer un recu par ID", description = "Retourne les details d'un recu specifique")
    @GetMapping("/{id}")
    public ResponseEntity<RecuDTO> getById(@Parameter(description = "ID du recu") @PathVariable Long id) {
        return service.findById(id)
                .map(r -> ResponseEntity.ok(mapper.toDto(r)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Recus d'un paiement", description = "Liste tous les recus associes a un paiement")
    @GetMapping("/paiement/{paiementId}")
    public ResponseEntity<List<RecuDTO>> getByPaiementId(@Parameter(description = "ID du paiement") @PathVariable Long paiementId) {
        List<RecuDTO> dtos = service.findByPaiementId(paiementId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Generer recu d'inscription", description = "Genere un recu PDF pour les frais d'inscription")
    @ApiResponse(responseCode = "201", description = "Recu genere avec succes")
    @PostMapping("/inscription")
    public ResponseEntity<RecuDTO> genererRecuInscription(
            @Parameter(description = "ID du paiement") @RequestParam Long paiementId,
            @Parameter(description = "ID du secretaire") @RequestParam UUID secretaireId) {
        Recu recu = service.genererRecuInscription(paiementId, secretaireId);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(recu));
    }

    @Operation(summary = "Generer recu de paiement", description = "Genere un recu PDF pour un paiement de scolarite")
    @ApiResponse(responseCode = "201", description = "Recu genere avec succes")
    @PostMapping("/paiement")
    public ResponseEntity<RecuDTO> genererRecuPaiement(
            @Parameter(description = "ID du paiement") @RequestParam Long paiementId,
            @Parameter(description = "ID du secretaire") @RequestParam UUID secretaireId,
            @Parameter(description = "Chemin du fichier PDF") @RequestParam String cheminFichier) {
        Recu recu = service.genererRecuPaiement(paiementId, secretaireId, cheminFichier);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(recu));
    }

    @PostMapping("/demander")
    public ResponseEntity<RecuDTO> demanderRecuNumerise(
            @RequestParam Long paiementId,
            @RequestParam UUID directeurId) {
        Recu recu = service.demanderRecuNumerise(paiementId, directeurId);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(recu));
    }

    @PostMapping("/{id}/traiter")
    public ResponseEntity<RecuDTO> traiterRecuDemande(
            @PathVariable Long id,
            @RequestParam UUID secretaireId,
            @RequestParam String cheminFichier) {
        try {
            Recu recu = service.traiterRecuDemande(id, secretaireId, cheminFichier);
            return ResponseEntity.ok(mapper.toDto(recu));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/paiement/{paiementId}/tous")
    public ResponseEntity<List<RecuDTO>> obtenirRecusParPaiement(@PathVariable Long paiementId) {
        List<RecuDTO> dtos = service.obtenirRecusParPaiement(paiementId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}


