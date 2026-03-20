package com.sophia.backend.application.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LogDTO extends BaseDTO {
    private UUID uuid;
    private UUID utilisateurUuid;
    private String action;
    private String entite;
    private String entiteId;
    private JsonNode anciennesValeurs;
    private JsonNode nouvellesValeurs;
    private String adresseIp;
    private String userAgent;
    private LocalDateTime dateAction;
    private String description;
}