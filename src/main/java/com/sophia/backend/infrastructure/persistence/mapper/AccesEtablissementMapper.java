package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.AccesEtablissementDTO;
import com.sophia.backend.domain.model.AccesEtablissement;
import com.sophia.backend.infrastructure.persistence.entity.AccesEtablissementEntity;
import org.springframework.stereotype.Component;

@Component
public class AccesEtablissementMapper {
    public AccesEtablissementDTO toDto(AccesEtablissement a) {
        if (a == null) return null;
        AccesEtablissementDTO dto = new AccesEtablissementDTO();
        dto.setId(a.getId());
        dto.setUtilisateurId(a.getUtilisateurId());
        dto.setEtablissementId(a.getEtablissementId());
        dto.setCreatedAt(a.getCreatedAt());
        dto.setUpdatedAt(a.getUpdatedAt());
        return dto;
    }

    public AccesEtablissement toDomain(AccesEtablissementDTO d) {
        if (d == null) return null;
        AccesEtablissement a = new AccesEtablissement();
        a.setId(d.getId());
        a.setUtilisateurId(d.getUtilisateurId());
        a.setEtablissementId(d.getEtablissementId());
        // Ne pas mapper createdAt, updatedAt (READ_ONLY)
        return a;
    }

    public AccesEtablissement toDomain(AccesEtablissementEntity e) {
        if (e == null) return null;
        AccesEtablissement a = new AccesEtablissement();
        a.setId(e.getId());
        a.setUtilisateurId(e.getUtilisateurId());
        a.setEtablissementId(e.getEtablissementId());
        a.setCreatedAt(e.getCreatedAt());
        a.setUpdatedAt(e.getUpdatedAt());
        return a;
    }

    public AccesEtablissementEntity toEntity(AccesEtablissement a) {
        if (a == null) return null;
        AccesEtablissementEntity e = new AccesEtablissementEntity();
        e.setId(a.getId());
        e.setUtilisateurId(a.getUtilisateurId());
        e.setEtablissementId(a.getEtablissementId());
        e.setCreatedAt(a.getCreatedAt());
        e.setUpdatedAt(a.getUpdatedAt());
        return e;
    }
}
