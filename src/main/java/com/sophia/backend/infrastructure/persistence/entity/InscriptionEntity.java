package com.sophia.backend.infrastructure.persistence.entity;

import com.sophia.backend.domain.enums.StatutInscription;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "inscriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uuid;

    private UUID eleveUuid;
    private UUID niveauUuid;
    private UUID anneeScolaireUuid;
    private LocalDate dateInscription;

    @Enumerated(EnumType.STRING)
    private StatutInscription statut;

    @Column(columnDefinition = "text")
    private String commentaire;

    // Gestion des blocages
    private boolean bloqueExamen;
    private boolean bloqueEvaluation;
    private String raisonBlocage;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
