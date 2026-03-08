package com.sophia.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FraisDivers {
    private Long id;
    private Long niveauId; // null si applicable à tous
    private String libelle;
    private BigDecimal montant;
    private Long anneeScolaireId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
