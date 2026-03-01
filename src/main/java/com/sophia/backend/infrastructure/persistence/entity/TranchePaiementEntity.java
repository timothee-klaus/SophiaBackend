package com.sophia.backend.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tranche_paiement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TranchePaiementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fraisScolaireId;
    private String nomTranche;
    private BigDecimal montant;
    private LocalDate dateLimiteDebut;
    private LocalDate dateLimiteFin;
    private Integer ordre;
    private LocalDateTime createdAt;
}

