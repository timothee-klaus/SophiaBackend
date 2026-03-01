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
    private Long id;
    private TypeNotification type;
    private UUID expediteur_id;
    private UUID destinataire_id;
    private String contenu;
    private boolean lu;
    private LocalDateTime date_creation;
    private String lien;
    private LocalDateTime created_at;
}

