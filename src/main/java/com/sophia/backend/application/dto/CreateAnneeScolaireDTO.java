package com.sophia.backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO pour la CRÉATION d'une année scolaire
 * N'accepte que les champs nécessaires
 * Les champs uid et estActive sont générés/calculés par le système
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAnneeScolaireDTO {
    private String libelle;
    private LocalDate dateDebut;
    private LocalDate dateFin;
}

