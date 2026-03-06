package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.FraisDiversDTO;
import com.sophia.backend.domain.model.FraisDivers;
import com.sophia.backend.infrastructure.persistence.entity.FraisDiversEntity;
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

    public FraisDivers toDomain(FraisDiversEntity e) {
        if (e == null) return null;
        FraisDivers f = new FraisDivers();
        f.setId(e.getId());
        f.setNiveauId(e.getNiveauId());
        f.setLibelle(e.getLibelle());
        f.setMontant(e.getMontant());
        f.setAnneeScolaireId(e.getAnneeScolaireId());
        f.setCreatedAt(e.getCreatedAt());
        return f;
    }

    public FraisDiversEntity toEntity(FraisDivers f) {
        if (f == null) return null;
        FraisDiversEntity e = new FraisDiversEntity();
        e.setId(f.getId());
        e.setNiveauId(f.getNiveauId());
        e.setLibelle(f.getLibelle());
        e.setMontant(f.getMontant());
        e.setAnneeScolaireId(f.getAnneeScolaireId());
        e.setCreatedAt(f.getCreatedAt());
        return e;
    }
}

