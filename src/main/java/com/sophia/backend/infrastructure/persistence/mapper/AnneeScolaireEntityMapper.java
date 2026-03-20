package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.domain.model.AnneeScolaire;
import com.sophia.backend.infrastructure.persistence.entity.AnneeScolaireEntity;
import org.springframework.stereotype.Component;

@Component
public class AnneeScolaireEntityMapper {
    public AnneeScolaire toDomain(AnneeScolaireEntity e) {
        if (e == null) return null;
        AnneeScolaire d = new AnneeScolaire();
        d.setId(e.getId());
        d.setUuid(e.getUuid());
        d.setLibelle(e.getLibelle());
        d.setDateDebut(e.getDateDebut());
        d.setDateFin(e.getDateFin());
        // estActive est calculé, pas mappé
        d.setCreatedAt(e.getCreatedAt());
        d.setUpdatedAt(e.getUpdatedAt());
        return d;
    }

    public AnneeScolaireEntity toEntity(AnneeScolaire d) {
        if (d == null) return null;
        AnneeScolaireEntity e = new AnneeScolaireEntity();
        e.setId(d.getId());
        e.setUuid(d.getUuid());
        e.setLibelle(d.getLibelle());
        e.setDateDebut(d.getDateDebut());
        e.setDateFin(d.getDateFin());
        // estActive est calculé, pas mappé
        e.setCreatedAt(d.getCreatedAt());
        e.setUpdatedAt(d.getUpdatedAt());
        return e;
    }
}
