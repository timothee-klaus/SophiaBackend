package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.FraisDiversDTO;
import com.sophia.backend.domain.model.FraisDivers;
import com.sophia.backend.infrastructure.persistence.entity.FraisDiversEntity;
import org.springframework.stereotype.Component;

@Component
public class FraisDiversMapper {
    public FraisDiversDTO toDto(FraisDivers f) {
        if (f == null) return null;
        FraisDiversDTO dto = new FraisDiversDTO();
        dto.setId(f.getId());
        dto.setNiveauId(f.getNiveauId());
        dto.setLibelle(f.getLibelle());
        dto.setMontant(f.getMontant());
        dto.setAnneeScolaireId(f.getAnneeScolaireId());
        dto.setCreatedAt(f.getCreatedAt());
        dto.setUpdatedAt(f.getUpdatedAt());
        return dto;
    }

    public FraisDivers toDomain(FraisDiversDTO d) {
        if (d == null) return null;
        FraisDivers f = new FraisDivers();
        f.setId(d.getId());
        f.setNiveauId(d.getNiveauId());
        f.setLibelle(d.getLibelle());
        f.setMontant(d.getMontant());
        f.setAnneeScolaireId(d.getAnneeScolaireId());
        // Ne pas mapper createdAt, updatedAt (READ_ONLY)
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
        f.setUpdatedAt(e.getUpdatedAt());
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
        e.setUpdatedAt(f.getUpdatedAt());
        return e;
    }
}
