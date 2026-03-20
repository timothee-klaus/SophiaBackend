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
        dto.setUuid(f.getUuid());
        dto.setNiveauUuid(f.getNiveauUuid());
        dto.setDescription(f.getDescription());
        dto.setMontant(f.getMontant());
        dto.setAnneeScolaireUuid(f.getAnneeScolaireUuid());
        dto.setCreatedAt(f.getCreatedAt());
        dto.setUpdatedAt(f.getUpdatedAt());
        return dto;
    }

    public FraisDivers toDomain(FraisDiversDTO d) {
        if (d == null) return null;
        FraisDivers f = new FraisDivers();
        f.setUuid(d.getUuid());
        f.setNiveauUuid(d.getNiveauUuid());
        f.setDescription(d.getDescription());
        f.setMontant(d.getMontant());
        f.setAnneeScolaireUuid(d.getAnneeScolaireUuid());
        f.setCreatedAt(d.getCreatedAt());
        f.setUpdatedAt(d.getUpdatedAt());
        return f;
    }

    public FraisDivers toDomain(FraisDiversEntity e) {
        if (e == null) return null;
        FraisDivers f = new FraisDivers();
        f.setId(e.getId());
        f.setUuid(e.getUuid());
        f.setNiveauId(e.getNiveauId());
        f.setNiveauUuid(e.getNiveauUuid());
        f.setDescription(e.getDescription());
        f.setMontant(e.getMontant());
        f.setAnneeScolaireId(e.getAnneeScolaireId());
        f.setAnneeScolaireUuid(e.getAnneeScolaireUuid());
        f.setCreatedAt(e.getCreatedAt());
        f.setUpdatedAt(e.getUpdatedAt());
        return f;
    }

    public FraisDiversEntity toEntity(FraisDivers f) {
        if (f == null) return null;
        FraisDiversEntity e = new FraisDiversEntity();
        e.setId(f.getId());
        e.setUuid(f.getUuid());
        e.setNiveauId(f.getNiveauId());
        e.setNiveauUuid(f.getNiveauUuid());
        e.setDescription(f.getDescription());
        e.setMontant(f.getMontant());
        e.setAnneeScolaireId(f.getAnneeScolaireId());
        e.setAnneeScolaireUuid(f.getAnneeScolaireUuid());
        e.setCreatedAt(f.getCreatedAt());
        e.setUpdatedAt(f.getUpdatedAt());
        return e;
    }
}
