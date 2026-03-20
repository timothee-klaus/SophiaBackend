package com.sophia.backend.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sophia.backend.domain.enums.ModePaiement;
import com.sophia.backend.domain.enums.TypePaiement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PaiementDTO extends BaseDTO {
    /**
     * UUID unique du paiement
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID uuid;
    
    /**
     * UUID de l'inscription
     */
    private UUID inscriptionUuid;
    
    private TypePaiement typePaiement;
    
    /**
     * UUID de la référence (tranche, frais divers, frais inscription)
     */
    private UUID referenceUuid;
    
    private BigDecimal montant;
    private LocalDateTime datePaiement;
    private ModePaiement modePaiement;
    private String commentaire;
}
