package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.AccesEtablissementDTO;
import com.sophia.backend.domain.model.AccesEtablissement;
import org.springframework.stereotype.Component;

@Component
public class AccesEtablissementMapper {
    public AccesEtablissementDTO toDto(AccesEtablissement a) {
        if (a == null) return null;
        return new AccesEtablissementDTO(a.getId(), a.getUtilisateurId(), a.getEtablissementId(), a.getCreatedAt());
    }

    public AccesEtablissement toDomain(AccesEtablissementDTO d) {
        if (d == null) return null;
        AccesEtablissement a = new AccesEtablissement();
        a.setId(d.getId());
        a.setUtilisateurId(d.getUtilisateurId());
        a.setEtablissementId(d.getEtablissementId());
        a.setCreatedAt(d.getCreatedAt());
        return a;
    }
}
