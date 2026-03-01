package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.FraisScolaireDTO;
import com.sophia.backend.domain.model.FraisScolaire;
import org.springframework.stereotype.Component;

@Component
public class FraisScolaireMapper {
    public FraisScolaireDTO toDto(FraisScolaire f) {
        if (f == null) return null;
        return new FraisScolaireDTO(f.getId(), f.getNiveauId(), f.getAnneeScolaireId(), f.getMontantTotal(), f.getDescription(), f.getCreatedAt(), f.getUpdatedAt());
    }

    public FraisScolaire toDomain(FraisScolaireDTO d) {
        if (d == null) return null;
        FraisScolaire f = new FraisScolaire();
        f.setId(d.getId());
        f.setNiveauId(d.getNiveauId());
        f.setAnneeScolaireId(d.getAnneeScolaireId());
        f.setMontantTotal(d.getMontantTotal());
        f.setDescription(d.getDescription());
        f.setCreatedAt(d.getCreatedAt());
        f.setUpdatedAt(d.getUpdatedAt());
        return f;
    }
}
