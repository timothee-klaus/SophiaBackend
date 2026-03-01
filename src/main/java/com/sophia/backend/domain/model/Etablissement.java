package com.sophia.backend.domain.model;

import com.sophia.backend.domain.enums.StatutEtablissement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etablissement {
    private Long id;
    private String nom;
    private String adresse;
    private String telephone;
    private String email;
    private String logo;
    private LocalDate date_creation;
    private StatutEtablissement statut;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}

