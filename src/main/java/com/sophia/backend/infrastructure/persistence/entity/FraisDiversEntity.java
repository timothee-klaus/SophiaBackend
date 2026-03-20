package com.sophia.backend.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "frais_divers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FraisDiversEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uuid;

    private Long niveauId; // nullable
    @Column(nullable = true)
    private UUID niveauUuid;
    @Column(columnDefinition = "text")
    private String description;
    private BigDecimal montant;
    private Long anneeScolaireId;
    @Column(name = "annee_scolaire_uuid", nullable = true)
    private UUID anneeScolaireUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "annee_scolaire_uuid", referencedColumnName = "uuid", insertable = false, updatable = false)
    private AnneeScolaireEntity anneeScolaire;
}
