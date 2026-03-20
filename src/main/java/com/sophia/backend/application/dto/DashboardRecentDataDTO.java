package com.sophia.backend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO contenant les données récentes pour le Dashboard
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardRecentDataDTO {

    /**
     * Dernières 5 inscriptions (triées par date décroissante)
     */
    private List<DashboardRecentInscriptionDTO> recentInscriptions;

    /**
     * Derniers 5 paiements (triés par date décroissante)
     */
    private List<DashboardRecentPaiementDTO> recentPayments;
}

