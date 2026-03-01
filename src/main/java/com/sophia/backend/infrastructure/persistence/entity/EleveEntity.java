package com.sophia.backend.infrastructure.persistence.entity;

import com.sophia.backend.domain.enums.Sexe;
import com.sophia.backend.domain.enums.StatutDossier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "eleve")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EleveEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
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
    private String photoPath;
    private String acteNaissancePath;
    private Long etablissementId;

    @Enumerated(EnumType.STRING)
    private StatutDossier statutDossier;

    private LocalDate dateCreationDossier;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

