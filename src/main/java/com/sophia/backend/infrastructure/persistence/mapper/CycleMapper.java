package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.CycleDTO;
import com.sophia.backend.domain.model.Cycle;
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
}
