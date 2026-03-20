package com.sophia.backend.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TranchePaiementDTO extends BaseDTO {
    /**
     * UUID unique de la tranche de paiement
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID uuid;
    
    /**
     * UUID du frais scolaire
     */
    private UUID fraisScolaireUuid;
    
    private String nomTranche;
    private BigDecimal montant;
    private LocalDate dateLimiteDebut;
    private LocalDate dateLimiteFin;
    private Integer ordre;
}
