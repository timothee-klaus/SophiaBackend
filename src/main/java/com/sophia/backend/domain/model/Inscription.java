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
    private UUID eleveId;
    private Long niveauId;
    private Long anneeScolaireId;
    private LocalDate dateInscription;
    private StatutInscription statut;
    private String commentaire;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
