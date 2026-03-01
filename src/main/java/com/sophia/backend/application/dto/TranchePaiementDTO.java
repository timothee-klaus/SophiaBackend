package com.sophia.backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TranchePaiementDTO {
    private Long id;
    private Long fraisScolaireId;
    private String nomTranche;
    private BigDecimal montant;
    private LocalDate dateLimiteDebut;
    private LocalDate dateLimiteFin;
    private Integer ordre;
    private LocalDateTime createdAt;
}
