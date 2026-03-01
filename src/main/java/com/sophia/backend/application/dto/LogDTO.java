package com.sophia.backend.application.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogDTO {
    private Long id;
    private UUID utilisateurId;
    private String action;
    private String entite;
    private String entiteId;
    private JsonNode anciennesValeurs;
    private JsonNode nouvellesValeurs;
    private String ipAdresse;
    private String userAgent;
    private LocalDateTime dateAction;
    private String description;
}
