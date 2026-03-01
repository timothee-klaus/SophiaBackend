package com.sophia.backend.domain.model;

import com.sophia.backend.domain.enums.TypeBlocage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blocage {
    private Long id;
    private Long inscriptionId;
    private TypeBlocage typeBlocage;
    private String raison;
    private LocalDate dateDebut;
    private LocalDate dateFin; // null si actif
    private boolean estActif;
    private UUID levePar;
    private LocalDateTime dateLevee;
    private LocalDateTime createdAt;
}
