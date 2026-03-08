package com.sophia.backend.domain.model;

import com.sophia.backend.domain.enums.RecuStatut;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recu {
    private Long id;
    private Long paiementId;
    private UUID demandePar;
    private LocalDateTime dateDemande;
    private UUID traitePar; // qui a traité (Directeur/Secrétaire)
    private LocalDateTime dateTraitement;
    private RecuStatut statut;
    private String cheminFichier;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
