package com.sophia.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnneeScolaire {
    private Long id;
    private String libelle;
    private LocalDate date_debut;
    private LocalDate date_fin;
    private boolean est_active;
    private LocalDateTime created_at;
}

