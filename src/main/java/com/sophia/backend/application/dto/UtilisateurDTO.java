package com.sophia.backend.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class UtilisateurDTO extends BaseDTO {
    private UUID id;
    private String nom;
    private String email;
    private String motDePasse;
    private String role;
    private String telephone;
    private boolean estActif;
}
