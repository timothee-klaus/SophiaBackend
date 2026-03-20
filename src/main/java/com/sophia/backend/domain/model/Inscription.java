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
    private Long id; // interne, non exposé API
    private UUID uuid;
    private UUID eleveUuid;
    private UUID niveauUuid;
    private UUID anneeScolaireUuid;
    private LocalDate dateInscription;
    private StatutInscription statut;
    private String commentaire;

    // Gestion des blocages
    private boolean bloqueExamen;  // Bloqué pour examens
    private boolean bloqueEvaluation;  // Bloqué pour évaluations
    private String raisonBlocage;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Alias explicites pour compatibilité avec les services/contrôleurs existants
    public UUID getEleveUuid() { return eleveUuid; }
    public UUID getAnneeScolaireUuid() { return anneeScolaireUuid; }
    public UUID getNiveauUuid() { return niveauUuid; }
}
