package com.sophia.backend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO représentant la répartition des élèves par cycle
 * Utilisé pour le graphique donut
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardRepartitionCycleDTO {

    /**
     * Nom du cycle (ex: "Collège", "Lycée", "Primaire")
     */
    private String cycle;

    /**
     * Nombre d'élèves inscrits dans ce cycle
     */
    private Long count;

    /**
     * Pourcentage d'élèves dans ce cycle (0-100)
     */
    private Double percentage;
}

