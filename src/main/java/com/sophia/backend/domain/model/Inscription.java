package com.sophia.backend.domain.model;

import com.sophia.backend.domain.enums.StatutInscription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inscription {
    private Long id;
    private UUID eleve_id;
    private Long niveau_id;
    private Long annee_scolaire_id;
    private LocalDate date_inscription;
    private StatutInscription statut;
    private String commentaire;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}

