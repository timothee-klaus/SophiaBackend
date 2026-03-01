package com.sophia.backend.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "frais_scolaire")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FraisScolaireEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long niveauId;
    private Long anneeScolaireId;
    private BigDecimal montantTotal;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

