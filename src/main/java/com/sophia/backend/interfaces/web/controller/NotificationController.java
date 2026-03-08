package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.NotificationDTO;
import com.sophia.backend.application.service.NotificationService;
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

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Créer une notification", description = "Crée une nouvelle notification")
    public ResponseEntity<NotificationDTO> create(@RequestBody NotificationDTO dto) {
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @Operation(summary = "Lister les notifications", description = "Liste toutes les notifications")
    public ResponseEntity<List<NotificationDTO>> getAll() {
        return ResponseEntity.ok(List.of());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une notification", description = "Récupère les détails d'une notification")
    public ResponseEntity<NotificationDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new NotificationDTO());
    }

    @GetMapping("/non-lues")
    @Operation(summary = "Notifications non lues", description = "Récupère les notifications non lues pour l'utilisateur courant")
    public ResponseEntity<List<NotificationDTO>> getNonLues() {
        return ResponseEntity.ok(List.of());
    }

    @GetMapping("/utilisateur/{utilisateurId}/non-lues")
    @Operation(summary = "Notifications non lues d'un utilisateur", description = "Récupère les notifications non lues pour un utilisateur spécifique")
    public ResponseEntity<List<NotificationDTO>> getNonLuesByUtilisateur(@PathVariable UUID utilisateurId) {
        return ResponseEntity.ok(List.of());
    }

    @PostMapping("/{id}/marquer-lu")
    @Operation(summary = "Marquer comme lu", description = "Marque une notification comme lue")
    public ResponseEntity<NotificationDTO> marquerCommeElu(@PathVariable Long id) {
        return ResponseEntity.ok(new NotificationDTO());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une notification", description = "Supprime une notification")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
