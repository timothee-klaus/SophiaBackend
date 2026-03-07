package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.NotificationDTO;
import com.sophia.backend.application.service.NotificationService;
import com.sophia.backend.domain.model.Notification;
import com.sophia.backend.infrastructure.persistence.mapper.NotificationMapper;
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

@Tag(name = "Notifications", description = "Gestion des notifications - Alertes, demandes de recus, notifications de disponibilite")
@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;
    private final NotificationMapper mapper;

    @Operation(summary = "Lister toutes les notifications", description = "Retourne toutes les notifications du systeme")
    @ApiResponse(responseCode = "200", description = "Liste des notifications")
    @GetMapping
    public ResponseEntity<List<NotificationDTO>> getAll() {
        List<NotificationDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Recuperer une notification par ID", description = "Retourne les details d'une notification")
    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTO> getById(@Parameter(description = "ID de la notification") @PathVariable Long id) {
        return service.findById(id)
                .map(n -> ResponseEntity.ok(mapper.toDto(n)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Notifications d'un destinataire", description = "Liste toutes les notifications envoyees a un utilisateur")
    @GetMapping("/destinataire/{destinataireId}")
    public ResponseEntity<List<NotificationDTO>> getByDestinataireId(@Parameter(description = "ID du destinataire (UUID)") @PathVariable UUID destinataireId) {
        List<NotificationDTO> dtos = service.findByDestinataireId(destinataireId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Notifications non lues", description = "Liste les notifications non lues d'un utilisateur")
    @GetMapping("/destinataire/{destinataireId}/non-lues")
    public ResponseEntity<List<NotificationDTO>> getUnreadByDestinataireId(@Parameter(description = "ID du destinataire") @PathVariable UUID destinataireId) {
        List<NotificationDTO> dtos = service.findUnreadByDestinataireId(destinataireId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Creer une notification", description = "Cree et envoie une nouvelle notification")
    @ApiResponse(responseCode = "201", description = "Notification creee")
    @PostMapping
    public ResponseEntity<NotificationDTO> create(@RequestBody NotificationDTO dto) {
        Notification notification = mapper.toDomain(dto);
        Notification saved = service.create(notification);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @PostMapping("/notifier-recu")
    public ResponseEntity<NotificationDTO> notifierRecuDisponible(
            @RequestParam UUID directeurId,
            @RequestParam UUID secretaireId,
            @RequestParam String lien) {
        Notification notification = service.notifierReçuDisponible(directeurId, secretaireId, lien);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(notification));
    }

    @PostMapping("/alerter-impayes")
    public ResponseEntity<NotificationDTO> alerterImpayesCritiques(
            @RequestParam UUID directeurId,
            @RequestParam int nombreImpayés) {
        Notification notification = service.alerterImpayesCritiques(directeurId, nombreImpayés);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(notification));
    }

    @PostMapping("/{id}/marquer-lue")
    public ResponseEntity<NotificationDTO> marquerCommeLue(@PathVariable Long id) {
        try {
            Notification notification = service.marquerCommeLue(id);
            return ResponseEntity.ok(mapper.toDto(notification));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/destinataire/{destinataireId}/notifications-non-lues")
    public ResponseEntity<List<NotificationDTO>> obtenirNotificationsNonLues(@PathVariable UUID destinataireId) {
        List<NotificationDTO> dtos = service.obtenirNotificationsNonLues(destinataireId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationDTO> update(@PathVariable Long id, @RequestBody NotificationDTO dto) {
        Notification notification = mapper.toDomain(dto);
        Notification updated = service.update(notification);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}


