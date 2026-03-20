package com.sophia.backend.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FraisScolaireDTO extends BaseDTO {
    /**
     * UUID unique des frais scolaires
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID uuid;
    
    /**
     * UUID du niveau
     */
    private UUID niveauUuid;
    
    /**
     * UUID de l'année scolaire
     */
    private UUID anneeScolaireUuid;
    
    private BigDecimal montantTotal;
    private String description;
}
