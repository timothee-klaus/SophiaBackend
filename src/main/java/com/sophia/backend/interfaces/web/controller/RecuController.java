package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.RecuDTO;
import com.sophia.backend.application.service.RecuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recus")
@Tag(name = "Reçus", description = "Gestion des reçus (inscription, paiements)")
public class RecuController {

    private final RecuService service;

    public RecuController(RecuService service) {
        this.service = service;
    }

    @Operation(summary = "Générer reçu d'inscription", description = "Génère un reçu pour les frais d'inscription")
    @PostMapping("/inscription")
    public ResponseEntity<RecuDTO> genererRecuInscription(@RequestParam Long paiementId) {
        return ResponseEntity.ok(new RecuDTO());
    }

    @Operation(summary = "Générer reçu de paiement", description = "Génère un reçu de paiement (PDF) à remettre au parent")
    @PostMapping("/paiement")
    public ResponseEntity<RecuDTO> genererRecuPaiement(@RequestParam Long paiementId) {
        return ResponseEntity.ok(new RecuDTO());
    }

    @Operation(summary = "Consulter un reçu", description = "Récupère les détails d'un reçu")
    @GetMapping("/{id}")
    public ResponseEntity<RecuDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new RecuDTO());
    }

    @Operation(summary = "Lister les reçus", description = "Liste tous les reçus")
    @GetMapping
    public ResponseEntity<List<RecuDTO>> getAll() {
        return ResponseEntity.ok(List.of());
    }

    @Operation(summary = "Télécharger un reçu", description = "Télécharge/récupère le PDF du reçu")
    @PostMapping("/{id}/telecharger")
    public ResponseEntity<byte[]> telechargerRecu(@PathVariable Long id) {
        return ResponseEntity.ok(new byte[0]);
    }

    @Operation(summary = "Scanner/téléverser reçu signé", description = "Permet de télécharger un reçu déjà signé")
    @PostMapping("/{id}/upload-signe")
    public ResponseEntity<RecuDTO> uploadRecuSigne(@PathVariable Long id) {
        return ResponseEntity.ok(new RecuDTO());
    }

    @Operation(summary = "Modifier un reçu", description = "Modifie les détails d'un reçu")
    @PutMapping("/{id}")
    public ResponseEntity<RecuDTO> update(@PathVariable Long id, @RequestBody RecuDTO dto) {
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Supprimer un reçu", description = "Supprime un reçu")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
