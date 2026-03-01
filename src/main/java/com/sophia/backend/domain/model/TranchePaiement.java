package com.sophia.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TranchePaiement {
    private Long id;
    private Long frais_scolaire_id;
    private String nom_tranche;
    private BigDecimal montant;
    private LocalDate date_limite_debut;
    private LocalDate date_limite_fin;
    private Integer ordre;
    private LocalDateTime created_at;
}

