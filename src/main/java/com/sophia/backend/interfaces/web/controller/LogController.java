package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.LogDTO;
import com.sophia.backend.application.service.LogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/logs")
@Tag(name = "Logs", description = "Gestion des logs d'audit (création, modification, consultation)")
public class LogController {

    private final LogService service;

    public LogController(LogService service) {
        this.service = service;
    }

    @Operation(summary = "Lister les logs", description = "Liste tous les logs d'audit du système")
    @GetMapping
    public ResponseEntity<List<LogDTO>> getAll() {
        return ResponseEntity.ok(List.of());
    }

    @Operation(summary = "Récupérer un log", description = "Récupère les détails d'un log d'audit")
    @GetMapping("/{id}")
    public ResponseEntity<LogDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new LogDTO());
    }

    @Operation(summary = "Logs d'un utilisateur", description = "Liste les actions effectuées par un utilisateur spécifique")
    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<List<LogDTO>> getByUtilisateur(@PathVariable UUID utilisateurId) {
        return ResponseEntity.ok(List.of());
    }

    @Operation(summary = "Logs d'une entité", description = "Liste les modifications apportées à une entité spécifique")
    @GetMapping("/entite/{entite}/{entiteId}")
    public ResponseEntity<List<LogDTO>> getByEntite(@PathVariable String entite, @PathVariable String entiteId) {
        return ResponseEntity.ok(List.of());
    }

    @Operation(summary = "Logs par action", description = "Liste les logs filtrés par type d'action (CREATE, UPDATE, DELETE, etc.)")
    @GetMapping("/action/{action}")
    public ResponseEntity<List<LogDTO>> getByAction(@PathVariable String action) {
        return ResponseEntity.ok(List.of());
    }

    @Operation(summary = "Supprimer un log", description = "Supprime un log d'audit")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
