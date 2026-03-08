package com.sophia.backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RecuDTO extends BaseDTO {
    private Long id;
    private Long paiementId;
    private UUID demandePar;
    private LocalDateTime dateDemande;
    private UUID traitePar;
    private LocalDateTime dateTraitement;
    private String statut;
    private String cheminFichier;
}
