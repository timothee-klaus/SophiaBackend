package com.sophia.backend.infrastructure.persistence.entity;

import com.sophia.backend.domain.enums.Sexe;
import com.sophia.backend.domain.enums.StatutDossier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "eleves")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EleveEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    private String matricule;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String lieuNaissance;

    @Enumerated(EnumType.STRING)
    private Sexe sexe;

    private String nationalite;
    private String adresse;
    private String nomTuteur;
    private String telephoneTuteur;
    private String emailTuteur;

    // Documents fournis (gérés par checkbox)
    private boolean photoFournie;
    private boolean acteNaissanceFourni;
    private boolean certificatResidenceFourni;
    private boolean bulletinsFournis;

    @Enumerated(EnumType.STRING)
    private StatutDossier statutDossier;

    private LocalDate dateCreationDossier;
}
