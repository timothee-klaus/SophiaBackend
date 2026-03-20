package com.sophia.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FraisInscription {
    private UUID uuid;
    private Long id; // interne
    private Long cycleId; // interne éventuel
    private UUID cycleUuid;
    private Long niveauId; // optionnel interne
    private UUID niveauUuid;
    private Long anneeScolaireId; // interne
    private UUID anneeScolaireUuid;
    private BigDecimal montant;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
