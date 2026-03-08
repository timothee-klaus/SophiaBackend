package com.sophia.backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InscriptionDTO extends BaseDTO {
    private Long id;
    private UUID eleveId;
    private Long niveauId;
    private Long anneeScolaireId;
    private LocalDate dateInscription;
    private String statut;
    private String commentaire;
}
