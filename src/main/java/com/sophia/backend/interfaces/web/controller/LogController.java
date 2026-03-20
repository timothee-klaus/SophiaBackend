package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.LogDTO;
import com.sophia.backend.application.service.LogService;
import com.sophia.backend.domain.enums.ActionLog;
import com.sophia.backend.infrastructure.persistence.mapper.LogMapper;
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
    private final LogMapper mapper;

    public LogController(LogService service, LogMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "Lister les logs", description = "Liste tous les logs d'audit du système")
    @GetMapping
    public ResponseEntity<List<LogDTO>> getAll() {
        List<LogDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Récupérer un log", description = "Récupère les détails d'un log d'audit")
    @GetMapping("/{uuid}")
    public ResponseEntity<LogDTO> getByUuid(@PathVariable UUID uuid) {
        return service.findByUuid(uuid)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Logs d'un utilisateur", description = "Liste les actions effectuées par un utilisateur spécifique")
    @GetMapping("/utilisateur/{utilisateurUuid}")
    public ResponseEntity<List<LogDTO>> getByUtilisateur(@PathVariable UUID utilisateurUuid) {
        List<LogDTO> dtos = service.findByUtilisateurId(utilisateurUuid).stream()
                .map(mapper::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Logs d'une entité", description = "Liste les modifications apportées à une entité spécifique")
    @GetMapping("/entite/{entite}/{entiteId}")
    public ResponseEntity<List<LogDTO>> getByEntite(@PathVariable String entite, @PathVariable String entiteId) {
        List<LogDTO> dtos = service.findByEntiteAndEntiteId(entite, entiteId).stream()
                .map(mapper::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Logs par action", description = "Liste les logs filtrés par type d'action (CREATE, UPDATE, DELETE, etc.)")
    @GetMapping("/action/{action}")
    public ResponseEntity<List<LogDTO>> getByAction(@PathVariable String action) {
        ActionLog actionLog = ActionLog.valueOf(action);
        List<LogDTO> dtos = service.findByAction(actionLog).stream()
                .map(mapper::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Supprimer un log", description = "Supprime un log d'audit")
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        service.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}
