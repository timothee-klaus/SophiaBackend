package com.sophia.backend.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sophia.backend.domain.enums.StatutInscription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InscriptionDTO extends BaseDTO {
    /**
     * UID unique de l'inscription
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID uuid;
    
    /**
     * UUID de l'élève
     */
    private UUID eleveUuid;
    
    /**
     * UUID du niveau
     */
    private UUID niveauUuid;
    
    /**
     * UUID de l'année scolaire
     */
    private UUID anneeScolaireUuid;
    
    private LocalDate dateInscription;
    private StatutInscription statut;
    private String commentaire;

    // Blocages
    private boolean bloqueExamen;
    private boolean bloqueEvaluation;
    private String raisonBlocage;
}
