package com.sophia.backend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sophia.backend.domain.enums.StatutInscription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO pour une inscription récente affichée au Dashboard
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardRecentInscriptionDTO {

    /**
     * UUID unique de l'inscription
     */
    private UUID uuid;

    /**
     * UUID de l'élève associé
     */
    private UUID eleveUuid;

    /**
     * Nom complet de l'élève (calculé: nom + prenom)
     */
    private String studentFullName;

    /**
     * UUID du niveau/classe
     */
    private UUID niveauUuid;

    /**
     * Libellé du niveau/classe
     */
    private String niveauLabel;

    /**
     * UUID de l'année scolaire
     */
    private UUID anneeScolaireUuid;

    /**
     * Libellé de l'année scolaire
     */
    private String anneeScolaireLabel;

    /**
     * Date d'inscription
     */
    private LocalDate dateInscription;

    /**
     * Statut de l'inscription
     */
    private StatutInscription statut;

    /**
     * Indicateur de blocage pour examen
     */
    private boolean bloqueExamen;

    /**
     * Indicateur de blocage pour évaluation
     */
    private boolean bloqueEvaluation;

    /**
     * Timestamp de création (pour afficher "créé il y a X minutes/heures")
     */
    private LocalDateTime createdAt;
}

