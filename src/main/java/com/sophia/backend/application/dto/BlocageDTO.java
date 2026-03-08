package com.sophia.backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BlocageDTO extends BaseDTO {
    private Long id;
    private Long inscriptionId;
    private String typeBlocage;
    private String raison;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private boolean estActif;
    private UUID levePar;
    private LocalDateTime dateLevee;
}
