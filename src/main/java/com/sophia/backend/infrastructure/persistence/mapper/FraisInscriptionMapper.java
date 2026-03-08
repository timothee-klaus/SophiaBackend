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
        dto.setId(f.getId());
        dto.setCycleId(f.getCycleId());
        dto.setNiveauId(f.getNiveauId());
        dto.setAnneeScolaireId(f.getAnneeScolaireId());
        dto.setMontant(f.getMontant());
        dto.setCreatedAt(f.getCreatedAt());
        dto.setUpdatedAt(f.getUpdatedAt());
        return dto;
    }

    public FraisInscription toDomain(FraisInscriptionDTO d) {
        if (d == null) return null;
        FraisInscription f = new FraisInscription();
        f.setId(d.getId());
        f.setCycleId(d.getCycleId());
        f.setNiveauId(d.getNiveauId());
        f.setAnneeScolaireId(d.getAnneeScolaireId());
        f.setMontant(d.getMontant());
        // Ne pas mapper createdAt/updatedAt (READ_ONLY)
        return f;
    }

    public FraisInscription toDomain(FraisInscriptionEntity e) {
        if (e == null) return null;
        FraisInscription f = new FraisInscription();
        f.setId(e.getId());
        f.setCycleId(e.getCycleId());
        f.setNiveauId(e.getNiveauId());
        f.setAnneeScolaireId(e.getAnneeScolaireId());
        f.setMontant(e.getMontant());
        f.setCreatedAt(e.getCreatedAt());
        f.setUpdatedAt(e.getUpdatedAt());
        return f;
    }

    public FraisInscriptionEntity toEntity(FraisInscription f) {
        if (f == null) return null;
        FraisInscriptionEntity e = new FraisInscriptionEntity();
        e.setId(f.getId());
        e.setCycleId(f.getCycleId());
        e.setNiveauId(f.getNiveauId());
        e.setAnneeScolaireId(f.getAnneeScolaireId());
        e.setMontant(f.getMontant());
        e.setCreatedAt(f.getCreatedAt());
        e.setUpdatedAt(f.getUpdatedAt());
        return e;
    }
}

