package com.sophia.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FraisScolaire {
    private Long id;
    private Long niveau_id;
    private Long annee_scolaire_id;
    private BigDecimal montant_total;
    private String description;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}

