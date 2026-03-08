package com.sophia.backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PaiementDTO extends BaseDTO {
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
}
