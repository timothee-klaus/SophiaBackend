package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.FraisInscriptionDTO;
import com.sophia.backend.domain.model.FraisInscription;
import com.sophia.backend.infrastructure.persistence.entity.FraisInscriptionEntity;
import org.springframework.stereotype.Component;

@Component
public class FraisInscriptionMapper {
    public FraisInscriptionDTO toDto(FraisInscription f) {
        if (f == null) return null;
        FraisInscriptionDTO dto = new FraisInscriptionDTO();
        dto.setUuid(f.getUuid());
        dto.setCycleUuid(f.getCycleUuid());
        dto.setNiveauUuid(f.getNiveauUuid());
        dto.setAnneeScolaireUuid(f.getAnneeScolaireUuid());
        dto.setMontant(f.getMontant());
        dto.setDescription(f.getDescription());
        dto.setCreatedAt(f.getCreatedAt());
        dto.setUpdatedAt(f.getUpdatedAt());
        return dto;
    }

    public FraisInscription toDomain(FraisInscriptionDTO d) {
        if (d == null) return null;
        FraisInscription f = new FraisInscription();
        f.setUuid(d.getUuid());
        f.setCycleUuid(d.getCycleUuid());
        f.setNiveauUuid(d.getNiveauUuid());
        f.setAnneeScolaireUuid(d.getAnneeScolaireUuid());
        f.setMontant(d.getMontant());
        f.setDescription(d.getDescription());
        f.setCreatedAt(d.getCreatedAt());
        f.setUpdatedAt(d.getUpdatedAt());
        return f;
    }

    public FraisInscription toDomain(FraisInscriptionEntity e) {
        if (e == null) return null;
        FraisInscription f = new FraisInscription();
        f.setId(e.getId());
        f.setUuid(e.getUuid());
        f.setCycleUuid(e.getCycleUuid());
        f.setNiveauId(e.getNiveauId());
        f.setNiveauUuid(e.getNiveauUuid());
        f.setAnneeScolaireId(e.getAnneeScolaireId());
        f.setAnneeScolaireUuid(e.getAnneeScolaireUuid());
        f.setMontant(e.getMontant());
        f.setDescription(e.getDescription());
        f.setCreatedAt(e.getCreatedAt());
        f.setUpdatedAt(e.getUpdatedAt());
        return f;
    }

    public FraisInscriptionEntity toEntity(FraisInscription f) {
        if (f == null) return null;
        FraisInscriptionEntity e = new FraisInscriptionEntity();
        e.setId(f.getId());
        e.setUuid(f.getUuid());
        e.setCycleUuid(f.getCycleUuid());
        e.setNiveauId(f.getNiveauId());
        e.setNiveauUuid(f.getNiveauUuid());
        e.setAnneeScolaireId(f.getAnneeScolaireId());
        e.setAnneeScolaireUuid(f.getAnneeScolaireUuid());
        e.setMontant(f.getMontant());
        e.setDescription(f.getDescription());
        e.setCreatedAt(f.getCreatedAt());
        e.setUpdatedAt(f.getUpdatedAt());
        return e;
    }
}
