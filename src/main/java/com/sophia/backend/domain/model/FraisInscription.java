package com.sophia.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FraisInscription {
    private Long id;
    private Long cycleId; // ou niveau_id selon besoin
    private Long niveauId; // optionnel
    private Long anneeScolaireId;
    private BigDecimal montant;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
