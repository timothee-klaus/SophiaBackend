package com.sophia.backend.domain.model;

import com.sophia.backend.domain.enums.Sexe;
import com.sophia.backend.domain.enums.StatutDossier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Eleve {
    private UUID id;
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
    private String photoPath;
    private String acteNaissancePath;
    private Long etablissementId;
    private StatutDossier statutDossier;
    private LocalDate dateCreationDossier;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
