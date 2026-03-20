package com.sophia.backend.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class AnneeScolaireDTO extends BaseDTO {
    /**
     * UID exposé à l'API
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID uuid;
    
    private String libelle;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    
    /**
     * Calculé automatiquement : true si la date actuelle
     * est entre dateDebut et dateFin (READ-ONLY)
     */
    private boolean estActive;
}
