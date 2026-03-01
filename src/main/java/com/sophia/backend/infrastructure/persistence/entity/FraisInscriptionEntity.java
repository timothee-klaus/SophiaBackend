package com.sophia.backend.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "frais_inscription")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FraisInscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cycleId;
    private Long niveauId;
    private Long anneeScolaireId;
    private BigDecimal montant;
    private LocalDateTime createdAt;
}

