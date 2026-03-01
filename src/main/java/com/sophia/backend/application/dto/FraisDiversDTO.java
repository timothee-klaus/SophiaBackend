package com.sophia.backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FraisDiversDTO {
    private Long id;
    private Long niveauId;
    private String libelle;
    private BigDecimal montant;
    private Long anneeScolaireId;
    private LocalDateTime createdAt;
}
