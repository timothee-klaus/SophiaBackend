package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.NiveauDTO;
import com.sophia.backend.domain.model.Niveau;
import org.springframework.stereotype.Component;

@Component
public class NiveauMapper {
    public NiveauDTO toDto(Niveau n) {
        if (n == null) return null;
        return new NiveauDTO(n.getId(), n.getNom(), n.getCycleId(), n.getEtablissementId(), n.getOrdre(), n.getCreatedAt());
    }

    public Niveau toDomain(NiveauDTO d) {
        if (d == null) return null;
        Niveau n = new Niveau();
        n.setId(d.getId());
        n.setNom(d.getNom());
        n.setCycleId(d.getCycleId());
        n.setEtablissementId(d.getEtablissementId());
        n.setOrdre(d.getOrdre());
        n.setCreatedAt(d.getCreatedAt());
        return n;
    }
}
