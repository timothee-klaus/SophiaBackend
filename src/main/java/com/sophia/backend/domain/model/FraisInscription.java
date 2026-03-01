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
    private Long cycle_id; // ou niveau_id selon besoin
    private Long niveau_id; // optionnel
    private Long annee_scolaire_id;
    private BigDecimal montant;
    private LocalDateTime created_at;
}

