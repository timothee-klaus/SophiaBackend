package com.sophia.backend.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FraisScolaireEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long niveauId;
    private Long anneeScolaireId;

    @Column(unique = true, nullable = false)
    private UUID uuid;

    @Column(nullable = false)
    private UUID niveauUuid;

    @Column(nullable = false)
    private UUID anneeScolaireUuid;

    @Column(nullable = false)
    private BigDecimal montantTotal;

    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

