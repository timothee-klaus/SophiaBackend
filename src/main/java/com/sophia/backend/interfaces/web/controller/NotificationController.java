package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.NotificationDTO;
import com.sophia.backend.application.service.NotificationService;
import com.sophia.backend.infrastructure.persistence.mapper.NotificationMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/notifications")
@Tag(name = "Notifications", description = "Gestion des notifications (reçus disponibles, alertes impayés)")
public class NotificationController {

    private final NotificationService service;
    private final NotificationMapper mapper;

    public NotificationController(NotificationService service, NotificationMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @Operation(summary = "Créer une notification", description = "Crée une nouvelle notification")
    public ResponseEntity<NotificationDTO> create(@RequestBody NotificationDTO dto) {
        dto.setUuid(null);
        var created = service.create(mapper.toDomain(dto));
        return ResponseEntity.ok(mapper.toDto(created));
    }

    @GetMapping
    @Operation(summary = "Lister les notifications", description = "Liste toutes les notifications")
    public ResponseEntity<List<NotificationDTO>> getAll() {
        List<NotificationDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/non-lues")
    @Operation(summary = "Notifications non lues", description = "Récupère les notifications non lues pour l'utilisateur courant")
    public ResponseEntity<List<NotificationDTO>> getNonLues(@RequestParam("destinataireUuid") UUID destinataireUuid) {
        List<NotificationDTO> dtos = service.findNonLues(destinataireUuid).stream()
                .map(mapper::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{uuid}")
    @Operation(summary = "Récupérer une notification", description = "Récupère les détails d'une notification")
    public ResponseEntity<NotificationDTO> getByUuid(@PathVariable UUID uuid) {
        return service.findByUuid(uuid)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "Supprimer une notification", description = "Supprime une notification")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        service.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}
