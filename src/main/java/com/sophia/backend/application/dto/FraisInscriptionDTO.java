package com.sophia.backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FraisInscriptionDTO {
    private Long id;
    private Long cycleId;
    private Long niveauId;
    private Long anneeScolaireId;
    private BigDecimal montant;
    private LocalDateTime createdAt;
}
