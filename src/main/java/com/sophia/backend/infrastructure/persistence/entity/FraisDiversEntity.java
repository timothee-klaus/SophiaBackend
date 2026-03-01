package com.sophia.backend.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "frais_divers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FraisDiversEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long niveauId; // nullable
    private String libelle;
    private BigDecimal montant;
    private Long anneeScolaireId;
    private LocalDateTime createdAt;
}

