package com.sophia.backend.infrastructure.persistence.entity;

import com.sophia.backend.domain.enums.RecuStatut;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "recu")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RecuEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long paiementId;
    private UUID demandePar;
    private LocalDateTime dateDemande;
    private UUID traitePar;
    private LocalDateTime dateTraitement;

    @Enumerated(EnumType.STRING)
    private RecuStatut statut;

    private String cheminFichier;
    private LocalDateTime createdAt;
}

