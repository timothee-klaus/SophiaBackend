package com.sophia.backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaiementDTO {
    private Long id;
    private Long inscriptionId;
    private String typePaiement;
    private Long referenceId;
    private BigDecimal montant;
    private LocalDateTime datePaiement;
    private String modePaiement;
    private String recuPath;
    private String commentaire;
    private UUID utilisateurId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
