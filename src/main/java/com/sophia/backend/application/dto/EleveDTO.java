package com.sophia.backend.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sophia.backend.domain.enums.Sexe;
import com.sophia.backend.domain.enums.StatutDossier;
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
public class EleveDTO extends BaseDTO {
    /**
     * UID unique de l'élève exposé à l'API
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID uuid;
    private String matricule;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String lieuNaissance;
    private Sexe sexe;
    private String nationalite;
    private String adresse;
    private String nomTuteur;
    private String telephoneTuteur;
    private String emailTuteur;

    // Documents fournis (checkbox)
    private boolean photoFournie;
    private boolean acteNaissanceFourni;
    private boolean certificatResidenceFourni;
    private boolean bulletinsFournis;

    private StatutDossier statutDossier;
    private LocalDate dateCreationDossier;
}
