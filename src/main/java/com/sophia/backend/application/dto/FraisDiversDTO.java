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
public class FraisDiversDTO extends BaseDTO {
    private Long id;
    private Long niveauId;
    private String libelle;
    private BigDecimal montant;
    private Long anneeScolaireId;
}
