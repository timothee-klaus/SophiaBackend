package com.sophia.backend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO pour le résumé global du Dashboard
 * Contient les KPIs, données agrégées et informations visuelles
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardSummaryDTO {

    /**
     * Indicateurs clés de performance (KPIs)
     */
    private DashboardKPIsDTO kpis;

    /**
     * Données récentes (dernières inscriptions et paiements)
     */
    private DashboardRecentDataDTO recentData;

    /**
     * Évolution des recettes pour les 30 derniers jours
     * Tableau de dates et montants pour le graphique en barres
     */
    private List<DashboardPaiementJourDTO> paiementsParJour;

    /**
     * Total des paiements sur les 7 derniers jours
     */
    private BigDecimal montantPaiementsJour;

    /**
     * Répartition des élèves par cycle
     * Données pour le graphique donut
     */
    private List<DashboardRepartitionCycleDTO> repartitionParCycle;

    /**
     * Journal d'audit - Dernières actions système (4-5 actions récentes)
     * Pour afficher le flux d'activité
     */
    private List<DashboardJournalAuditDTO> journalAudit;

    /**
     * Timestamp de génération du rapport
     */
    private LocalDateTime generatedAt;

    /**
     * Version de l'API pour suivi
     */
    private String apiVersion;
}
