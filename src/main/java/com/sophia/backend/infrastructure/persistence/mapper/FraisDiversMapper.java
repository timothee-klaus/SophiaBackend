package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.FraisDiversDTO;
import com.sophia.backend.domain.model.FraisDivers;
import org.springframework.stereotype.Component;

@Component
public class FraisDiversMapper {
    public FraisDiversDTO toDto(FraisDivers f) {
        if (f == null) return null;
        return new FraisDiversDTO(f.getId(), f.getNiveauId(), f.getLibelle(), f.getMontant(), f.getAnneeScolaireId(), f.getCreatedAt());
    }

    public FraisDivers toDomain(FraisDiversDTO d) {
        if (d == null) return null;
        FraisDivers f = new FraisDivers();
        f.setId(d.getId());
        f.setNiveauId(d.getNiveauId());
        f.setLibelle(d.getLibelle());
        f.setMontant(d.getMontant());
        f.setAnneeScolaireId(d.getAnneeScolaireId());
        f.setCreatedAt(d.getCreatedAt());
        return f;
    }
}
