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
public class FraisDivers {
    private Long id;
    private UUID uuid;
    private Long niveauId; // interne
    private UUID niveauUuid;
    private String description;
    private BigDecimal montant;
    private Long anneeScolaireId; // interne
    private UUID anneeScolaireUuid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
