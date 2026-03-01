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
    private Long paiement_id;
    private UUID demande_par;
    private LocalDateTime date_demande;
    private UUID traite_par; // qui a traité (Directeur/Secrétaire)
    private LocalDateTime date_traitement;
    private RecuStatut statut;
    private String chemin_fichier;
    private LocalDateTime created_at;
}

