package com.sophia.backend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO représentant une action du journal d'audit
 * Utilisé pour le flux d'activité récent du dashboard
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardJournalAuditDTO {

    /**
     * Description de l'action (ex: "Inscription validée", "Paiement reçu")
     */
    private String action;

    /**
     * Référence de l'élève ou de la ressource affectée (ex: "ELEV001" ou "Dupont Jean")
     */
    private String reference;

    /**
     * Temps écoulé en format lisible (ex: "il y a 2 heures", "il y a 5 minutes")
     */
    private String timeAgo;

    /**
     * Utilisateur qui a effectué l'action
     */
    private String user;
}

