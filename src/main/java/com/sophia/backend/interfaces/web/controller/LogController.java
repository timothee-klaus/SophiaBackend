package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.LogDTO;
import com.sophia.backend.application.service.LogService;
import com.sophia.backend.domain.model.Log;
import com.sophia.backend.infrastructure.persistence.mapper.LogMapper;
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

@Tag(name = "Logs", description = "Audit trail - Historique des actions (creation, modification, suppression, connexion)")
@RestController
@RequestMapping("/api/v1/logs")
@RequiredArgsConstructor
public class LogController {

    private final LogService service;
    private final LogMapper mapper;

    @Operation(summary = "Lister tous les logs", description = "Retourne l'historique complet des actions")
    @GetMapping
    public ResponseEntity<List<LogDTO>> getAll() {
        List<LogDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Recuperer un log par ID", description = "Retourne les details d'une action")
    @GetMapping("/{id}")
    public ResponseEntity<LogDTO> getById(@Parameter(description = "ID du log") @PathVariable Long id) {
        return service.findById(id)
                .map(l -> ResponseEntity.ok(mapper.toDto(l)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Logs par utilisateur", description = "Liste toutes les actions effectuees par un utilisateur")
    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<List<LogDTO>> getByUtilisateurId(@Parameter(description = "ID de l'utilisateur") @PathVariable UUID utilisateurId) {
        List<LogDTO> dtos = service.findByUtilisateurId(utilisateurId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Enregistrer une creation", description = "Enregistre un log de creation d'entite")
    @ApiResponse(responseCode = "200", description = "Log enregistre")
    @PostMapping("/enregistrer-creation")
    public ResponseEntity<Void> enregistrerCreation(
            @Parameter(description = "ID de l'utilisateur") @RequestParam UUID utilisateurId,
            @Parameter(description = "Type d'entite (ELEVE, PAIEMENT, etc.)") @RequestParam String entite,
            @RequestParam String entiteId,
            @RequestParam String description) {
        service.enregistrerCreation(utilisateurId, entite, entiteId, description);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/enregistrer-modification")
    public ResponseEntity<Void> enregistrerModification(
            @RequestParam UUID utilisateurId,
            @RequestParam String entite,
            @RequestParam String entiteId,
            @RequestParam String anciennes_valeurs,
            @RequestParam String nouvelles_valeurs,
            @RequestParam String description) {
        service.enregistrerModification(utilisateurId, entite, entiteId, anciennes_valeurs, nouvelles_valeurs, description);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/enregistrer-suppression")
    public ResponseEntity<Void> enregistrerSuppression(
            @RequestParam UUID utilisateurId,
            @RequestParam String entite,
            @RequestParam String entiteId,
            @RequestParam String description) {
        service.enregistrerSuppression(utilisateurId, entite, entiteId, description);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/enregistrer-connexion")
    public ResponseEntity<Void> enregistrerConnexion(
            @RequestParam UUID utilisateurId,
            @RequestParam String adresseIp,
            @RequestParam String userAgent) {
        service.enregistrerConnexion(utilisateurId, adresseIp, userAgent);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/enregistrer-deconnexion")
    public ResponseEntity<Void> enregistrerDeconnexion(@RequestParam UUID utilisateurId) {
        service.enregistrerDeconnexion(utilisateurId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/utilisateur/{utilisateurId}/historique")
    public ResponseEntity<List<LogDTO>> obtenirHistorique(@PathVariable UUID utilisateurId) {
        List<LogDTO> dtos = service.obtenirHistoriquUtilisateur(utilisateurId).stream()
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

