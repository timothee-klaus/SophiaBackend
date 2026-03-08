package com.sophia.backend.application.dto;

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
    private UUID id;
    private String matricule;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String lieuNaissance;
    private String sexe;
    private String nationalite;
    private String adresse;
    private String nomTuteur;
    private String telephoneTuteur;
    private String emailTuteur;
    private String photoPath;
    private String acteNaissancePath;
    private Long etablissementId;
    private String statutDossier;
    private LocalDate dateCreationDossier;
}
