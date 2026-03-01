package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.domain.enums.RoleUtilisateur;
import com.sophia.backend.domain.model.Utilisateur;
import com.sophia.backend.infrastructure.persistence.entity.UtilisateurEntity;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurEntityMapper {
    public Utilisateur toDomain(UtilisateurEntity e) {
        if (e == null) return null;
        Utilisateur u = new Utilisateur();
        u.setId(e.getId());
        u.setUuid(e.getUuid());
        u.setNom(e.getNom());
        u.setPrenom(e.getPrenom());
        u.setEmail(e.getEmail());
        u.setMotDePasse(e.getMotDePasse());
        u.setRole(e.getRole());
        u.setTelephone(e.getTelephone());
        u.setEstActif(e.isEstActif());
        u.setLastLogin(e.getLastLogin());
        u.setCreatedAt(e.getCreatedAt());
        u.setUpdatedAt(e.getUpdatedAt());
        u.setSupprime(e.isSupprime());
        return u;
    }

    public UtilisateurEntity toEntity(Utilisateur d) {
        if (d == null) return null;
        UtilisateurEntity e = new UtilisateurEntity();
        e.setId(d.getId());
        e.setUuid(d.getUuid());
        e.setNom(d.getNom());
        e.setPrenom(d.getPrenom());
        e.setEmail(d.getEmail());
        e.setMotDePasse(d.getMotDePasse());
        e.setRole(d.getRole());
        e.setTelephone(d.getTelephone());
        e.setEstActif(d.isEstActif());
        e.setLastLogin(d.getLastLogin());
        e.setCreatedAt(d.getCreatedAt());
        e.setUpdatedAt(d.getUpdatedAt());
        e.setSupprime(d.isSupprime());
        return e;
    }
}
