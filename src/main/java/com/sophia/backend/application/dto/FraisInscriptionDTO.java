package com.sophia.backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FraisInscriptionDTO extends BaseDTO {
    private Long id;
    private Long cycleId;
    private Long niveauId;
    private Long anneeScolaireId;
    private BigDecimal montant;
}
