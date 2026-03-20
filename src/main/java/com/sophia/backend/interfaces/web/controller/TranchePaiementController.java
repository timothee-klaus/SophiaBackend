package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.TranchePaiementDTO;
import com.sophia.backend.application.service.TranchePaiementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tranches-paiement")
@Tag(name = "Tranches de Paiement", description = "Gestion des tranches de paiement (1ère, 2ème, etc.)")
public class TranchePaiementController {

    private final TranchePaiementService service;

    public TranchePaiementController(TranchePaiementService service) {
        this.service = service;
    }

    @Operation(summary = "Créer tranche de paiement", description = "Crée une nouvelle tranche de paiement (1ère, 2ème, 3ème, etc.)")
    @PostMapping
    public ResponseEntity<TranchePaiementDTO> create(@RequestBody TranchePaiementDTO dto) {
        dto.setUuid(null);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Lister les tranches", description = "Retourne la liste de toutes les tranches de paiement")
    @GetMapping
    public ResponseEntity<List<TranchePaiementDTO>> getAll() {
        return ResponseEntity.ok(List.of());
    }

    @Operation(summary = "Tranches d'un frais scolaire", description = "Retourne les tranches associées à un frais scolaire spécifique")
    @GetMapping("/frais/{fraisUuid}")
    public ResponseEntity<List<TranchePaiementDTO>> getByFraisUuid(@PathVariable UUID fraisUuid) {
        return ResponseEntity.ok(List.of());
    }

    @Operation(summary = "Récupérer une tranche", description = "Retourne les détails d'une tranche de paiement")
    @GetMapping("/{uuid}")
    public ResponseEntity<TranchePaiementDTO> getByUuid(@PathVariable UUID uuid) {
        return ResponseEntity.ok(new TranchePaiementDTO());
    }

    @Operation(summary = "Modifier une tranche", description = "Modifie les informations d'une tranche de paiement")
    @PutMapping("/{uuid}")
    public ResponseEntity<TranchePaiementDTO> update(@PathVariable UUID uuid, @RequestBody TranchePaiementDTO dto) {
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Supprimer une tranche", description = "Supprime une tranche de paiement")
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        return ResponseEntity.noContent().build();
    }
}
