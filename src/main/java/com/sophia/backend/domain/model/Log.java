package com.sophia.backend.domain.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.sophia.backend.domain.model.enums.ActionLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entité métier représentant un log d'action dans le système.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    private long id;
    private ActionLog action;
    private String entite;
    private String entiteId;
    private JsonNode anciennesValeurs;
    private JsonNode nouvellesValeurs;
    private String ipAdresse;
    private String userAgent;
    private String dateAction;
    private String description;
    private long utilisateurId;
}
