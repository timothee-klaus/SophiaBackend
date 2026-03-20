package com.sophia.backend.domain.model;

import com.sophia.backend.domain.enums.TypeNotification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    private UUID uuid;
    private Long id;
    private TypeNotification type;
    private UUID expediteurUuid;
    private UUID destinataireUuid;
    private String contenu;
    private boolean lu;
    private LocalDateTime dateCreation;
    private String lien;  // Lien vers l'entité concernée (ex: /eleves/123)
    private LocalDateTime createdAt;
}
