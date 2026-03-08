package com.sophia.backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TranchePaiementDTO extends BaseDTO {
    private Long id;
    private Long fraisScolaireId;
    private String nomTranche;
    private BigDecimal montant;
    private LocalDate dateLimiteDebut;
    private LocalDate dateLimiteFin;
    private Integer ordre;
}

