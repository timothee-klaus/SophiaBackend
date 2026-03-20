package com.sophia.backend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO représentant les paiements pour un jour spécifique
 * Utilisé pour le graphique en barres de l'évolution des recettes
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardPaiementJourDTO {

    /**
     * Date au format YYYY-MM-DD
     */
    private String date;

    /**
     * Montant total des paiements pour cette journée
     */
    private java.math.BigDecimal montant;
}

