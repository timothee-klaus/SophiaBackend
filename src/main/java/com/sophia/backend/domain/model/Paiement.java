package com.sophia.backend.domain.model;

import com.sophia.backend.domain.enums.ModePaiement;
import com.sophia.backend.domain.enums.TypePaiement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paiement {
    private UUID uuid;
    private Long id; // interne, non exposé API
    private UUID inscriptionUuid;
    private TypePaiement typePaiement;
    private UUID referenceUuid;
    private BigDecimal montant;
    private LocalDateTime datePaiement;
    private ModePaiement modePaiement;
    private String commentaire;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
