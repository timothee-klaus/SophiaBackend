package com.sophia.backend.infrastructure.persistence.entity;

import com.sophia.backend.domain.enums.ModePaiement;
import com.sophia.backend.domain.enums.TypePaiement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "paiement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaiementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uuid;

    private UUID inscriptionUuid;
    private TypePaiement typePaiement;

    private UUID referenceUuid;
    private BigDecimal montant;
    private LocalDateTime datePaiement;

    @Enumerated(EnumType.STRING)
    private ModePaiement modePaiement;

    @Column(columnDefinition = "text")
    private String commentaire;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
