package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.domain.model.Niveau;
import com.sophia.backend.infrastructure.persistence.entity.NiveauEntity;
import org.springframework.stereotype.Component;

@Component
public class NiveauEntityMapper {
    public Niveau toDomain(NiveauEntity e) {
        if (e == null) return null;
        Niveau d = new Niveau();
        d.setId(e.getId());
        d.setNom(e.getNom());
        d.setCycleId(e.getCycleId());
        d.setEtablissementId(e.getEtablissementId());
        d.setOrdre(e.getOrdre());
        d.setCreatedAt(e.getCreatedAt());
        return d;
    }

    public NiveauEntity toEntity(Niveau d) {
        if (d == null) return null;
        NiveauEntity e = new NiveauEntity();
        e.setId(d.getId());
        e.setNom(d.getNom());
        e.setCycleId(d.getCycleId());
        e.setEtablissementId(d.getEtablissementId());
        e.setOrdre(d.getOrdre());
        e.setCreatedAt(d.getCreatedAt());
        return e;
    }
}
