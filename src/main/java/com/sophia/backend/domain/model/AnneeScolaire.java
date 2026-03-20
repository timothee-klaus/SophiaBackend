package com.sophia.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnneeScolaire {
    private Long id;
    private UUID uuid;
    private String libelle;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Détermine si l'année scolaire est actuellement active
     * basée sur les dates de début et fin comparées à aujourd'hui
     * 
     * @return true si la date actuelle est entre dateDebut et dateFin
     */
    public boolean isEstActive() {
        LocalDate today = LocalDate.now();
        return !today.isBefore(dateDebut) && !today.isAfter(dateFin);
    }
}
