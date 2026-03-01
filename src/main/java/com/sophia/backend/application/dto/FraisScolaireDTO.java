package com.sophia.backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FraisScolaireDTO {
    private Long id;
    private Long niveauId;
    private Long anneeScolaireId;
    private BigDecimal montantTotal;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
