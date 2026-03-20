package com.sophia.backend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO pour les Indicateurs Clés de Performance (KPIs) du Dashboard
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardKPIsDTO {

    /**
     * Nombre total d'élèves actifs avec au moins une inscription active
     */
    private Long totalStudentsActive;

    /**
     * Nombre total de tous les élèves enregistrés dans le système
     */
    private Long totalStudentsRegistered;

    /**
     * Nombre d'inscriptions pour l'année scolaire courante
     */
    private Long enrollmentsCurrentYear;

    /**
     * Nombre total d'inscriptions depuis le démarrage du système
     */
    private Long totalEnrollmentsAllTime;

    /**
     * Total des paiements encaissés ce mois-ci (en devise de l'établissement)
     */
    private BigDecimal paymentsThisMonth;

    /**
     * Total des paiements encaissés cette année scolaire
     */
    private BigDecimal paymentsThisYear;

    /**
     * Nombre de paiements enregistrés ce mois-ci
     */
    private Long paymentCountThisMonth;

    /**
     * Nombre de paiements enregistrés cette année
     */
    private Long paymentCountThisYear;

    /**
     * Montant total des frais dus (non encore payés) par les élèves
     */
    private BigDecimal totalAmountDue;

    /**
     * Taux de recouvrement pour l'année courante (en pourcentage)
     * = (Paiements encaissés / Frais attendus) * 100
     */
    private Double recoveryRate;

    /**
     * Nombre d'élèves bloqués (inscriptions bloquées pour examen/évaluation)
     */
    private Long elevesBloques;

    /**
     * Nombre d'inscriptions avec statut en attente
     */
    private Long inscriptionsEnAttente;

    /**
     * Nombre de dossiers d'élèves avec statut INCOMPLET
     */
    private Long incompleteStudentFiles;
}


