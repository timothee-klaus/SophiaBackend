package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.CycleDTO;
import com.sophia.backend.domain.model.Cycle;
import com.sophia.backend.infrastructure.persistence.entity.CycleEntity;
import org.springframework.stereotype.Component;

@Component
public class CycleMapper {
    public CycleDTO toDto(Cycle c) {
        if (c == null) return null;
        return new CycleDTO(c.getId(), c.getNom(), c.getDescription(), c.getOrdre(), c.getCreatedAt());
    }

    public Cycle toDomain(CycleDTO d) {
        if (d == null) return null;
        Cycle c = new Cycle();
        c.setId(d.getId());
        c.setNom(d.getNom());
        c.setDescription(d.getDescription());
        c.setOrdre(d.getOrdre());
        c.setCreatedAt(d.getCreatedAt());
        return c;
    }

    public Cycle toDomain(CycleEntity e) {
        if (e == null) return null;
        Cycle c = new Cycle();
        c.setId(e.getId());
        c.setNom(e.getNom());
        c.setDescription(e.getDescription());
        c.setOrdre(e.getOrdre());
        c.setCreatedAt(e.getCreatedAt());
        return c;
    }

    public CycleEntity toEntity(Cycle c) {
        if (c == null) return null;
        CycleEntity e = new CycleEntity();
        e.setId(c.getId());
        e.setNom(c.getNom());
        e.setDescription(c.getDescription());
        e.setOrdre(c.getOrdre());
        e.setCreatedAt(c.getCreatedAt());
        return e;
    }
}

