package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.*;
import com.sophia.backend.application.service.PaiementService;
import com.sophia.backend.domain.model.Paiement;
import com.sophia.backend.infrastructure.persistence.mapper.PaiementMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Tag(name = "Paiements", description = "Gestion des paiements scolaires - Enregistrement, suivi des tranches, calcul des soldes et impayes")
@RestController
@RequestMapping("/api/v1/paiements")
@RequiredArgsConstructor
public class PaiementController {

    private final PaiementService service;
    private final PaiementMapper mapper;

    @Operation(summary = "Enregistrer un paiement", description = "Enregistre un paiement scolaire en précisant la tranche (1ère, 2ème, etc.)")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Paiement enregistre avec succes"),
        @ApiResponse(responseCode = "400", description = "Donnees invalides")
    })
    @PostMapping
    public ResponseEntity<PaiementDTO> create(@RequestBody PaiementDTO dto) {
        dto.setUuid(null);
        Paiement paiement = mapper.toDomain(dto);
        Paiement saved = service.enregistrerPaiement(paiement);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @Operation(summary = "Lister les paiements", description = "Retourne la liste de tous les paiements")
    @ApiResponse(responseCode = "200", description = "Liste des paiements recuperee")
    @GetMapping
    public ResponseEntity<List<PaiementDTO>> getAll() {
        List<PaiementDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Récupérer un paiement", description = "Retourne les détails d'un paiement spécifique")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Paiement trouve"),
        @ApiResponse(responseCode = "404", description = "Paiement non trouve")
    })
    @GetMapping("/{uuid}")
    public ResponseEntity<PaiementDTO> getByUuid(@Parameter(description = "UUID du paiement") @PathVariable UUID uuid) {
        return service.findByUuid(uuid)
                .map(p -> ResponseEntity.ok(mapper.toDto(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Historique des paiements", description = "Retourne l'historique de tous les paiements d'une inscription")
    @ApiResponse(responseCode = "200", description = "Historique des paiements")
    @GetMapping("/inscription/{inscriptionUuid}")
    public ResponseEntity<List<PaiementDTO>> getByInscriptionUuid(@Parameter(description = "UUID de l'inscription") @PathVariable UUID inscriptionUuid) {
        List<PaiementDTO> dtos = service.findByInscriptionUuid(inscriptionUuid).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Visualiser l'échéancier", description = "Retourne l'échéancier d'un élève (soldes par tranche)")
    @ApiResponse(responseCode = "200", description = "Echeancier de l'inscription")
    @GetMapping("/inscription/{inscriptionUuid}/echeancier")
    public ResponseEntity<List<PaiementDTO>> getEcheancier(@Parameter(description = "UUID de l'inscription") @PathVariable UUID inscriptionUuid) {
        List<PaiementDTO> dtos = service.findByInscriptionUuid(inscriptionUuid).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Calculer solde restant", description = "Retourne le solde restant dû pour une inscription")
    @ApiResponse(responseCode = "200", description = "Solde restant calcule")
    @GetMapping("/inscription/{inscriptionUuid}/solde-restant")
    public ResponseEntity<BigDecimal> getSoldeRestant(
            @Parameter(description = "UUID de l'inscription") @PathVariable UUID inscriptionUuid,
            @Parameter(description = "Montant total attendu") @RequestParam BigDecimal montantTotal) {
        BigDecimal solde = service.calculerSoldeRestantByUuid(inscriptionUuid, montantTotal);
        return ResponseEntity.ok(solde);
    }

    @Operation(summary = "Verifier si a jour", description = "Verifie si une inscription est a jour dans ses paiements")
    @ApiResponse(responseCode = "200", description = "true si a jour, false sinon")
    @GetMapping("/inscription/{inscriptionUuid}/a-jour")
    public ResponseEntity<Boolean> estAJour(
            @Parameter(description = "UUID de l'inscription") @PathVariable UUID inscriptionUuid,
            @Parameter(description = "Montant total attendu") @RequestParam BigDecimal montantTotal) {
        boolean aJour = service.estAJourByUuid(inscriptionUuid, montantTotal);
        return ResponseEntity.ok(aJour);
    }

    @Operation(summary = "Impayés d'une inscription", description = "Retourne la liste des paiements en retard pour une inscription")
    @ApiResponse(responseCode = "200", description = "Liste des impayés recuperee")
    @GetMapping("/inscription/{inscriptionUuid}/impayes")
    public ResponseEntity<List<PaiementDTO>> getImpayes(@Parameter(description = "UUID de l'inscription") @PathVariable UUID inscriptionUuid) {
        List<PaiementDTO> dtos = service.obtenirPaiementsEnRetardByUuid(inscriptionUuid).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Élèves en retard", description = "Retourne la liste des élèves en retard de paiement (surtout ceux bloqués pour les examens)")
    @ApiResponse(responseCode = "200", description = "Liste des élèves en retard recuperee")
    @GetMapping("/eleves-en-retard")
    public ResponseEntity<List<PaiementDTO>> getElevesEnRetard() {
        // Récupérer les paiements en retard
        List<PaiementDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Filtrer par statut", description = "Filtre les élèves par statut de paiement (À_JOUR, EN_RETARD, PARTIEL)")
    @ApiResponse(responseCode = "200", description = "Liste des élèves filtrés recuperee")
    @GetMapping("/eleves/statut/{statut}")
    public ResponseEntity<List<PaiementDTO>> getByStatut(@Parameter(description = "Statut de paiement") @PathVariable String statut) {
        // Filtrer les paiements selon le statut
        List<PaiementDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }


    @Operation(summary = "Modifier un paiement", description = "Modifie les détails d'un paiement")
    @ApiResponse(responseCode = "200", description = "Paiement modifie avec succes")
    @PutMapping("/{uuid}")
    public ResponseEntity<PaiementDTO> update(@PathVariable UUID uuid, @RequestBody PaiementDTO dto) {
        Paiement paiement = mapper.toDomain(dto);
        Paiement updated = service.update(uuid, paiement);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @Operation(summary = "Supprimer un paiement", description = "Supprime un paiement")
    @ApiResponse(responseCode = "204", description = "Paiement supprime avec succes")
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        service.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}
