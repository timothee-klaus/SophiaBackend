package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.UtilisateurDTO;
import com.sophia.backend.domain.enums.RoleUtilisateur;
import com.sophia.backend.domain.model.Utilisateur;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurMapper {
    public UtilisateurDTO toDto(Utilisateur u) {
        if (u == null) return null;
        // utiliser UUID du domaine (champ 'uuid') comme identifiant externe
        return new UtilisateurDTO(u.getUuid(), u.getNom(), u.getEmail(), u.getRole() != null ? u.getRole().name() : null, u.getTelephone(), u.isEstActif(), u.getLastLogin(), u.getCreatedAt(), u.getUpdatedAt());
    }

    public Utilisateur toDomain(UtilisateurDTO d) {
        if (d == null) return null;
        Utilisateur u = new Utilisateur();
        // mapper l'UUID du DTO vers le champ uuid du domaine
        u.setUuid(d.getId());
        u.setNom(d.getNom());
        u.setEmail(d.getEmail());
        if (d.getRole() != null) {
            try { u.setRole(RoleUtilisateur.valueOf(d.getRole())); } catch (IllegalArgumentException ex) {}
        }
        u.setTelephone(d.getTelephone());
        u.setEstActif(d.isEstActif());
        u.setLastLogin(d.getLastLogin());
        u.setCreatedAt(d.getCreatedAt());
        u.setUpdatedAt(d.getUpdatedAt());
        return u;
    }
}
