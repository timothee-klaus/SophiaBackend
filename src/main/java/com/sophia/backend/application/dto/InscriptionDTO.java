package com.sophia.backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscriptionDTO {
    private Long id;
    private UUID eleveId;
    private Long niveauId;
    private Long anneeScolaireId;
    private LocalDate dateInscription;
    private String statut;
    private String commentaire;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
