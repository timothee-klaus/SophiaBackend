package com.sophia.backend.application.dto;

import com.sophia.backend.domain.enums.ModePaiement;
import com.sophia.backend.domain.enums.TypePaiement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO pour les informations nécessaires à la génération d'un reçu (frontend)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoRecuDTO {
    // Info paiement
    private java.util.UUID paiementUuid;
    private BigDecimal montant;
    private LocalDateTime datePaiement;
    private ModePaiement modePaiement;
    private TypePaiement typePaiement;
    private String commentaire;

    // Info élève
    private String matriculeEleve;
    private String nomEleve;
    private String prenomEleve;

    // Info inscription
    private String niveau;
    private String anneeScolaire;

    // Info tranche (si applicable)
    private String nomTranche;
    private BigDecimal montantTranche;

    // Info frais (si applicable)
    private String libelleFrais;
    private BigDecimal montantFrais;
}

