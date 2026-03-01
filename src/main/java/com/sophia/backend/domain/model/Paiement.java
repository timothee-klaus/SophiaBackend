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
    private Long id;
    private Long inscriptionId;
    private TypePaiement typePaiement;
    private Long referenceId;
    private BigDecimal montant;
    private LocalDateTime datePaiement;
    private ModePaiement modePaiement;
    private String recuPath;
    private String commentaire;
    private UUID utilisateurId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
