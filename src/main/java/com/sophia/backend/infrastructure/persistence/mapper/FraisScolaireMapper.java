package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.FraisScolaireDTO;
import com.sophia.backend.domain.model.FraisScolaire;
import com.sophia.backend.infrastructure.persistence.entity.FraisScolaireEntity;
import org.springframework.stereotype.Component;

@Component
public class FraisScolaireMapper {
    public FraisScolaireDTO toDto(FraisScolaire f) {
        if (f == null) return null;
        FraisScolaireDTO dto = new FraisScolaireDTO();
        dto.setId(f.getId());
        dto.setNiveauId(f.getNiveauId());
        dto.setAnneeScolaireId(f.getAnneeScolaireId());
        dto.setMontantTotal(f.getMontantTotal());
        dto.setDescription(f.getDescription());
        dto.setCreatedAt(f.getCreatedAt());
        dto.setUpdatedAt(f.getUpdatedAt());
        return dto;
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

    public FraisScolaire toDomain(FraisScolaireEntity e) {
        if (e == null) return null;
        FraisScolaire f = new FraisScolaire();
        f.setId(e.getId());
        f.setNiveauId(e.getNiveauId());
        f.setAnneeScolaireId(e.getAnneeScolaireId());
        f.setMontantTotal(e.getMontantTotal());
        f.setDescription(e.getDescription());
        f.setCreatedAt(e.getCreatedAt());
        f.setUpdatedAt(e.getUpdatedAt());
        return f;
    }

    public FraisScolaireEntity toEntity(FraisScolaire f) {
        if (f == null) return null;
        FraisScolaireEntity e = new FraisScolaireEntity();
        e.setId(f.getId());
        e.setNiveauId(f.getNiveauId());
        e.setAnneeScolaireId(f.getAnneeScolaireId());
        e.setMontantTotal(f.getMontantTotal());
        e.setDescription(f.getDescription());
        e.setCreatedAt(f.getCreatedAt());
        e.setUpdatedAt(f.getUpdatedAt());
        return e;
    }
}

