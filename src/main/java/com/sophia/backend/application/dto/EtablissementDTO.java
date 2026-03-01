package com.sophia.backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtablissementDTO {
    private Long id;
    private String nom;
    private String adresse;
    private String telephone;
    private String email;
    private String logo;
    private LocalDate dateCreation;
    private String statut;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
