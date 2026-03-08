package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.FraisInscriptionDTO;
import com.sophia.backend.application.service.FraisInscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/frais-inscription")
@Tag(name = "Frais d'Inscription", description = "Gestion des frais d'inscription (5000, 10000, 3000 F)")
public class FraisInscriptionController {

    private final FraisInscriptionService service;

    public FraisInscriptionController(FraisInscriptionService service) {
        this.service = service;
    }

    @Operation(summary = "Créer frais d'inscription", description = "Crée une configuration de frais d'inscription")
    @PostMapping
    public ResponseEntity<FraisInscriptionDTO> create(@RequestBody FraisInscriptionDTO dto) {
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Lister les frais d'inscription", description = "Retourne la liste de tous les frais d'inscription configurés")
    @GetMapping
    public ResponseEntity<List<FraisInscriptionDTO>> getAll() {
        return ResponseEntity.ok(List.of());
    }

    @Operation(summary = "Frais d'inscription par année", description = "Retourne les frais d'inscription pour une année scolaire")
    @GetMapping("/annee/{anneId}")
    public ResponseEntity<List<FraisInscriptionDTO>> getByAnneeScolaire(@PathVariable Long anneId) {
        return ResponseEntity.ok(List.of());
    }

    @Operation(summary = "Récupérer frais d'inscription", description = "Récupère les détails d'une configuration de frais d'inscription")
    @GetMapping("/{id}")
    public ResponseEntity<FraisInscriptionDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new FraisInscriptionDTO());
    }

    @Operation(summary = "Modifier frais d'inscription", description = "Modifie une configuration de frais d'inscription")
    @PutMapping("/{id}")
    public ResponseEntity<FraisInscriptionDTO> update(@PathVariable Long id, @RequestBody FraisInscriptionDTO dto) {
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Supprimer frais d'inscription", description = "Supprime une configuration de frais d'inscription")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
