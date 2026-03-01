package com.sophia.backend.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "annee_scolaire")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnneeScolaireEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelle;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private boolean estActive;
    private LocalDateTime createdAt;
}

