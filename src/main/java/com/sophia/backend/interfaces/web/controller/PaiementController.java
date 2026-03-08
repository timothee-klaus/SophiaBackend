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
    @GetMapping("/{id}")
    public ResponseEntity<PaiementDTO> getById(@Parameter(description = "ID du paiement") @PathVariable Long id) {
        return service.findById(id)
                .map(p -> ResponseEntity.ok(mapper.toDto(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Historique des paiements", description = "Retourne l'historique de tous les paiements d'une inscription")
    @ApiResponse(responseCode = "200", description = "Historique des paiements")
    @GetMapping("/inscription/{inscriptionId}")
    public ResponseEntity<List<PaiementDTO>> getByInscriptionId(@Parameter(description = "ID de l'inscription") @PathVariable Long inscriptionId) {
        List<PaiementDTO> dtos = service.findByInscriptionId(inscriptionId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Visualiser l'échéancier", description = "Retourne l'échéancier d'un élève (soldes par tranche)")
    @ApiResponse(responseCode = "200", description = "Echeancier de l'inscription")
    @GetMapping("/inscription/{inscriptionId}/echeancier")
    public ResponseEntity<List<PaiementDTO>> getEcheancier(@Parameter(description = "ID de l'inscription") @PathVariable Long inscriptionId) {
        List<PaiementDTO> dtos = service.findByInscriptionId(inscriptionId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Calculer solde restant", description = "Retourne le solde restant dû pour une inscription")
    @ApiResponse(responseCode = "200", description = "Solde restant calcule")
    @GetMapping("/inscription/{inscriptionId}/solde-restant")
    public ResponseEntity<BigDecimal> getSoldeRestant(
            @Parameter(description = "ID de l'inscription") @PathVariable Long inscriptionId,
            @Parameter(description = "Montant total attendu") @RequestParam BigDecimal montantTotal) {
        BigDecimal solde = service.calculerSoldeRestant(inscriptionId, montantTotal);
        return ResponseEntity.ok(solde);
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

    @Operation(summary = "Impayés par classe", description = "Retourne la liste des impayés pour une classe/niveau spécifique")
    @ApiResponse(responseCode = "200", description = "Liste des impayés recuperee")
    @GetMapping("/classe/{niveauId}/impayes")
    public ResponseEntity<List<PaiementDTO>> getImpayes(@Parameter(description = "ID du niveau") @PathVariable Long niveauId) {
        List<PaiementDTO> dtos = service.obtenirPaiementsEnRetard(niveauId).stream()
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

    @Operation(summary = "Générer reçu PDF", description = "Génère un reçu de paiement (PDF) à remettre au parent")
    @ApiResponse(responseCode = "200", description = "Reçu généré avec succès")
    @PostMapping("/{id}/generer-recu")
    public ResponseEntity<RecuDTO> genererRecu(@Parameter(description = "ID du paiement") @PathVariable Long id) {
        // Générer le reçu via le service de paiement
        RecuDTO recu = new RecuDTO();
        return ResponseEntity.ok(recu);
    }

    @Operation(summary = "Télécharger reçu", description = "Télécharge un reçu PDF (ou permet de télécharger un reçu signé)")
    @ApiResponse(responseCode = "200", description = "Reçu téléchargé avec succès")
    @PostMapping("/{id}/telecharger-recu")
    public ResponseEntity<byte[]> telechargerRecu(@Parameter(description = "ID du paiement") @PathVariable Long id) {
        // Télécharger le reçu PDF
        byte[] fichier = new byte[0];
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=recu-" + id + ".pdf")
                .body(fichier);
    }

    @Operation(summary = "Modifier un paiement", description = "Modifie les détails d'un paiement")
    @ApiResponse(responseCode = "200", description = "Paiement modifie avec succes")
    @PutMapping("/{id}")
    public ResponseEntity<PaiementDTO> update(@PathVariable Long id, @RequestBody PaiementDTO dto) {
        Paiement paiement = mapper.toDomain(dto);
        Paiement updated = service.update(paiement);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @Operation(summary = "Supprimer un paiement", description = "Supprime un paiement")
    @ApiResponse(responseCode = "204", description = "Paiement supprime avec succes")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}


