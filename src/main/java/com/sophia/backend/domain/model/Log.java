package com.sophia.backend.domain.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.sophia.backend.domain.enums.ActionLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entité métier représentant un log d'action dans le système.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    private Long id;
    private UUID utilisateurId;
    private ActionLog action;
    private String entite;
    private String entiteId;
    private JsonNode anciennesValeurs;
    private JsonNode nouvellesValeurs;
    private String ipAdresse;
    private String userAgent;
    private LocalDateTime dateAction;
    private String description;
}
