package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.FraisInscriptionDTO;
import com.sophia.backend.domain.model.FraisInscription;
import com.sophia.backend.infrastructure.persistence.entity.FraisInscriptionEntity;
import org.springframework.stereotype.Component;

@Component
public class FraisInscriptionMapper {
    public FraisInscriptionDTO toDto(FraisInscription f) {
        if (f == null) return null;
        return new FraisInscriptionDTO(f.getId(), f.getCycleId(), f.getNiveauId(), f.getAnneeScolaireId(), f.getMontant(), f.getCreatedAt());
    }

    public FraisInscription toDomain(FraisInscriptionDTO d) {
        if (d == null) return null;
        FraisInscription f = new FraisInscription();
        f.setId(d.getId());
        f.setCycleId(d.getCycleId());
        f.setNiveauId(d.getNiveauId());
        f.setAnneeScolaireId(d.getAnneeScolaireId());
        f.setMontant(d.getMontant());
        f.setCreatedAt(d.getCreatedAt());
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
        return e;
    }
}

