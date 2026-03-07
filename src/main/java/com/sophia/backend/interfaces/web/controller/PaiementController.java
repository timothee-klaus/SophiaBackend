package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.PaiementDTO;
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
import java.util.stream.Collectors;

@Tag(name = "Paiements", description = "Gestion des paiements scolaires - Enregistrement, suivi des tranches, calcul des soldes et impayes")
@RestController
@RequestMapping("/api/v1/paiements")
@RequiredArgsConstructor
public class PaiementController {

    private final PaiementService service;
    private final PaiementMapper mapper;

    @Operation(summary = "Lister tous les paiements", description = "Retourne la liste de tous les paiements enregistres dans le systeme")
    @ApiResponse(responseCode = "200", description = "Liste des paiements recuperee")
    @GetMapping
    public ResponseEntity<List<PaiementDTO>> getAll() {
        List<PaiementDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Recuperer un paiement par ID", description = "Retourne les details d'un paiement specifique")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Paiement trouve"),
        @ApiResponse(responseCode = "404", description = "Paiement non trouve")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PaiementDTO> getById(@Parameter(description = "ID du paiement") @PathVariable Long id) {
        return service.findById(id)
                .map(p -> ResponseEntity.ok(mapper.toDto(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Paiements par inscription", description = "Retourne tous les paiements effectues pour une inscription")
    @ApiResponse(responseCode = "200", description = "Liste des paiements de l'inscription")
    @GetMapping("/inscription/{inscriptionId}")
    public ResponseEntity<List<PaiementDTO>> getByInscriptionId(@Parameter(description = "ID de l'inscription") @PathVariable Long inscriptionId) {
        List<PaiementDTO> dtos = service.findByInscriptionId(inscriptionId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Enregistrer un paiement", description = "Enregistre un nouveau paiement (inscription, scolarite ou divers) avec le mode de paiement")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Paiement enregistre avec succes"),
        @ApiResponse(responseCode = "400", description = "Donnees invalides")
    })
    @PostMapping
    public ResponseEntity<PaiementDTO> create(@RequestBody PaiementDTO dto) {
        Paiement paiement = mapper.toDomain(dto);
        Paiement saved = service.enregistrerPaiement(paiement);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @Operation(summary = "Associer paiement a une inscription", description = "Enregistre un paiement et l'associe a une inscription specifique")
    @ApiResponse(responseCode = "201", description = "Paiement associe avec succes")
    @PostMapping("/inscription/{inscriptionId}/enregistrer")
    public ResponseEntity<PaiementDTO> associerPaiement(
            @Parameter(description = "ID de l'inscription") @PathVariable Long inscriptionId,
            @RequestBody PaiementDTO dto) {
        Paiement paiement = mapper.toDomain(dto);
        Paiement saved = service.associerPaiementAInscription(inscriptionId, paiement);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @Operation(summary = "Visualiser l'echeancier", description = "Affiche l'echeancier de paiement d'une inscription (tranches payees et restantes)")
    @ApiResponse(responseCode = "200", description = "Echeancier de l'inscription")
    @GetMapping("/inscription/{inscriptionId}/echeancier")
    public ResponseEntity<List<PaiementDTO>> visualiserEcheancier(@Parameter(description = "ID de l'inscription") @PathVariable Long inscriptionId) {
        List<PaiementDTO> dtos = service.visualiserEchéancier(inscriptionId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Calculer le solde restant", description = "Calcule le montant restant a payer pour une inscription")
    @ApiResponse(responseCode = "200", description = "Solde restant calcule")
    @GetMapping("/inscription/{inscriptionId}/solde-restant")
    public ResponseEntity<BigDecimal> calculerSoldeRestant(
            @Parameter(description = "ID de l'inscription") @PathVariable Long inscriptionId,
            @Parameter(description = "Montant total attendu") @RequestParam BigDecimal montantTotal) {
        BigDecimal solde = service.calculerSoldeRestant(inscriptionId, montantTotal);
        return ResponseEntity.ok(solde);
    }

    @Operation(summary = "Historique des paiements", description = "Consulte l'historique complet des paiements d'une inscription")
    @ApiResponse(responseCode = "200", description = "Historique des paiements")
    @GetMapping("/inscription/{inscriptionId}/historique")
    public ResponseEntity<List<PaiementDTO>> obtenirHistorique(@Parameter(description = "ID de l'inscription") @PathVariable Long inscriptionId) {
        List<PaiementDTO> dtos = service.obtenirHistoriquePaiements(inscriptionId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Verifier si a jour", description = "Verifie si une inscription est a jour dans ses paiements")
    @ApiResponse(responseCode = "200", description = "true si a jour, false sinon")
    @GetMapping("/inscription/{inscriptionId}/a-jour")
    public ResponseEntity<Boolean> estAJour(
            @Parameter(description = "ID de l'inscription") @PathVariable Long inscriptionId,
            @Parameter(description = "Montant total attendu") @RequestParam BigDecimal montantTotal) {
        boolean aJour = service.estAJour(inscriptionId, montantTotal);
        return ResponseEntity.ok(aJour);
    }

    @Operation(summary = "Paiements en retard", description = "Liste les paiements en retard pour une inscription")
    @ApiResponse(responseCode = "200", description = "Liste des paiements en retard")
    @GetMapping("/inscription/{inscriptionId}/en-retard")
    public ResponseEntity<List<PaiementDTO>> obtenirPaiementsEnRetard(@Parameter(description = "ID de l'inscription") @PathVariable Long inscriptionId) {
        List<PaiementDTO> dtos = service.obtenirPaiementsEnRetard(inscriptionId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaiementDTO> update(@PathVariable Long id, @RequestBody PaiementDTO dto) {
        Paiement paiement = mapper.toDomain(dto);
        Paiement updated = service.update(paiement);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}


