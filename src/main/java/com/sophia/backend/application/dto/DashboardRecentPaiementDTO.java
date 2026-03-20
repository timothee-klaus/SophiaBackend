package com.sophia.backend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sophia.backend.domain.enums.ModePaiement;
import com.sophia.backend.domain.enums.TypePaiement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO pour un paiement récent affichée au Dashboard
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardRecentPaiementDTO {

    /**
     * UUID unique du paiement
     */
    private UUID uuid;

    /**
     * UUID de l'inscription associée
     */
    private UUID inscriptionUuid;

    /**
     * UUID de l'élève (via l'inscription)
     */
    private UUID eleveUuid;

    /**
     * Nom complet de l'élève (calculé: nom + prenom)
     */
    private String studentFullName;

    /**
     * Type de paiement (FRAIS_INSCRIPTION, FRAIS_SCOLARITE, etc.)
     */
    private TypePaiement typePaiement;

    /**
     * Montant du paiement
     */
    private BigDecimal montant;

    /**
     * Mode de paiement (ESPECES, CHEQUE, VIREMENT, etc.)
     */
    private ModePaiement modePaiement;

    /**
     * Date du paiement
     */
    private LocalDateTime datePaiement;

    /**
     * Commentaire/description du paiement
     */
    private String commentaire;

    /**
     * Timestamp de création (pour afficher "créé il y a X minutes/heures")
     */
    private LocalDateTime createdAt;
}

