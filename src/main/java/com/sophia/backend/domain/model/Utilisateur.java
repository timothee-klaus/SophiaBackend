package com.sophia.backend.domain.model;

import com.sophia.backend.domain.enums.RoleUtilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"motDePasse"})
public class Utilisateur {
    private long id;
    private UUID uuid;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private RoleUtilisateur role;
    private String telephone;
    private boolean estActif;
    private LocalDateTime lastLogin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean supprime;
}
