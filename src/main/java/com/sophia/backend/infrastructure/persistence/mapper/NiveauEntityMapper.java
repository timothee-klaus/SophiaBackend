package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.domain.model.Niveau;
import com.sophia.backend.infrastructure.persistence.entity.CycleEntity;
import com.sophia.backend.infrastructure.persistence.entity.NiveauEntity;
import org.springframework.stereotype.Component;

@Component
public class NiveauEntityMapper {

    public Niveau toDomain(NiveauEntity e) {
        if (e == null) return null;
        Niveau d = new Niveau();
        d.setId(e.getId());
        d.setUid(e.getUuid());
        d.setNom(e.getNom());
        if (e.getCycle() != null) {
            d.setCycleId(e.getCycle().getId());
            d.setCycleUuid(e.getCycle().getUuid());
            d.setCycleUid(e.getCycle().getUuid());
        }
        d.setEtablissementId(e.getEtablissementId());
        d.setOrdre(e.getOrdre());
        d.setCreatedAt(e.getCreatedAt());
        d.setUpdatedAt(e.getUpdatedAt());
        return d;
    }

    public NiveauEntity toEntity(Niveau d) {
        if (d == null) return null;
        NiveauEntity e = new NiveauEntity();
        e.setId(d.getId());
        e.setUuid(d.getUid());
        e.setNom(d.getNom());
        e.setEtablissementId(d.getEtablissementId());
        e.setOrdre(d.getOrdre());
        e.setCreatedAt(d.getCreatedAt());
        e.setUpdatedAt(d.getUpdatedAt());
        // Associer le cycle si disponible
        if (d.getCycleId() != null || d.getCycleUuid() != null) {
            CycleEntity c = new CycleEntity();
            c.setId(d.getCycleId());
            c.setUuid(d.getCycleUuid());
            e.setCycle(c);
        }
        return e;
    }
}
