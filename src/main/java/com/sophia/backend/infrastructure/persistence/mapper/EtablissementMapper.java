package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.EtablissementDTO;
import com.sophia.backend.domain.model.Etablissement;
import org.springframework.stereotype.Component;

@Component
public class EtablissementMapper {
    public EtablissementDTO toDto(Etablissement e) {
        if (e == null) return null;
        return new EtablissementDTO(
                e.getId(), e.getNom(), e.getAdresse(), e.getTelephone(), e.getEmail(), e.getLogo(), e.getDateCreation(), e.getStatut() != null ? e.getStatut().name() : null, e.getCreatedAt(), e.getUpdatedAt()
        );
    }

    public Etablissement toDomain(EtablissementDTO d) {
        if (d == null) return null;
        Etablissement e = new Etablissement();
        e.setId(d.getId());
        e.setNom(d.getNom());
        e.setAdresse(d.getAdresse());
        e.setTelephone(d.getTelephone());
        e.setEmail(d.getEmail());
        e.setLogo(d.getLogo());
        e.setDateCreation(d.getDateCreation());
        e.setCreatedAt(d.getCreatedAt());
        e.setUpdatedAt(d.getUpdatedAt());
        return e;
    }
}
