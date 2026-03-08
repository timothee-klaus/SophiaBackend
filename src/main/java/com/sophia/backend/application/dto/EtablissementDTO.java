package com.sophia.backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EtablissementDTO extends BaseDTO {
    private Long id;
    private String nom;
    private String adresse;
    private String telephone;
    private String email;
    private String logo;
    private LocalDate dateCreation;
    private String statut;
}
