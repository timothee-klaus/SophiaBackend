package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.UtilisateurDTO;
import com.sophia.backend.domain.enums.RoleUtilisateur;
import com.sophia.backend.domain.model.Utilisateur;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurMapper {
    public UtilisateurDTO toDto(Utilisateur u) {
        if (u == null) return null;
        UtilisateurDTO dto = new UtilisateurDTO();
        dto.setId(u.getUuid());
        dto.setNom(u.getNom());
        dto.setEmail(u.getEmail());
        dto.setRole(u.getRole() != null ? u.getRole().name() : null);
        dto.setTelephone(u.getTelephone());
        dto.setEstActif(u.isEstActif());
        dto.setLastLogin(u.getLastLogin());
        dto.setCreatedAt(u.getCreatedAt());
        dto.setUpdatedAt(u.getUpdatedAt());
        return dto;
    }

    public Utilisateur toDomain(UtilisateurDTO d) {
        if (d == null) return null;
        Utilisateur u = new Utilisateur();
        u.setUuid(d.getId());
        u.setNom(d.getNom());
        u.setEmail(d.getEmail());
        if (d.getRole() != null) {
            try { u.setRole(RoleUtilisateur.valueOf(d.getRole())); } catch (IllegalArgumentException ex) {}
        }
        u.setTelephone(d.getTelephone());
        u.setEstActif(d.isEstActif());
        // Ne pas mapper lastLogin, createdAt, updatedAt (READ_ONLY)
        return u;
    }
}
