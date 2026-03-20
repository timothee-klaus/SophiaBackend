package com.sophia.backend.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "annee_scolaire")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AnneeScolaireEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uuid;

    private String libelle;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    /**
     * Détermine si l'année scolaire est actuellement active
     * basée sur les dates de début/fin vs aujourd'hui
     * 
     * @return true si la date actuelle est entre dateDebut et dateFin
     */
    @Transient
    public boolean isEstActive() {
        LocalDate today = LocalDate.now();
        return !today.isBefore(dateDebut) && !today.isAfter(dateFin);
    }
}

