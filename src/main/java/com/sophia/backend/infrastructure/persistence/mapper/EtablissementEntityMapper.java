package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.domain.model.Etablissement;
import com.sophia.backend.infrastructure.persistence.entity.EtablissementEntity;
import org.springframework.stereotype.Component;

@Component
public class EtablissementEntityMapper {
    public Etablissement toDomain(EtablissementEntity e) {
        if (e == null) return null;
        Etablissement d = new Etablissement();
        d.setId(e.getId());
        d.setNom(e.getNom());
        d.setAdresse(e.getAdresse());
        d.setTelephone(e.getTelephone());
        d.setEmail(e.getEmail());
        d.setLogo(e.getLogo());
        d.setDateCreation(e.getDateCreation());
        d.setStatut(e.getStatut());
        d.setCreatedAt(e.getCreatedAt());
        d.setUpdatedAt(e.getUpdatedAt());
        return d;
    }

    public EtablissementEntity toEntity(Etablissement d) {
        if (d == null) return null;
        EtablissementEntity e = new EtablissementEntity();
        e.setId(d.getId());
        e.setNom(d.getNom());
        e.setAdresse(d.getAdresse());
        e.setTelephone(d.getTelephone());
        e.setEmail(d.getEmail());
        e.setLogo(d.getLogo());
        e.setDateCreation(d.getDateCreation());
        e.setStatut(d.getStatut());
        e.setCreatedAt(d.getCreatedAt());
        e.setUpdatedAt(d.getUpdatedAt());
        return e;
    }
}
