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
    private LocalDate date_naissance;
    private String lieu_naissance;
    private Sexe sexe;
    private String nationalite;
    private String adresse;
    private String nom_tuteur;
    private String telephone_tuteur;
    private String email_tuteur;
    private String photo_path;
    private String acte_naissance_path;
    private Long etablissement_id;
    private StatutDossier statut_dossier;
    private LocalDate date_creation_dossier;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}

