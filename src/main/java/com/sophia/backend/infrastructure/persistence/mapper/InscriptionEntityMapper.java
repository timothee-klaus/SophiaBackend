package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.domain.model.Inscription;
import com.sophia.backend.infrastructure.persistence.entity.InscriptionEntity;
import org.springframework.stereotype.Component;

@Component
public class InscriptionEntityMapper {
    public Inscription toDomain(InscriptionEntity e) {
        if (e == null) return null;
        Inscription d = new Inscription();
        d.setId(e.getId());
        d.setEleveId(e.getEleveId());
        d.setNiveauId(e.getNiveauId());
        d.setAnneeScolaireId(e.getAnneeScolaireId());
        d.setDateInscription(e.getDateInscription());
        d.setStatut(e.getStatut());
        d.setCommentaire(e.getCommentaire());
        d.setCreatedAt(e.getCreatedAt());
        d.setUpdatedAt(e.getUpdatedAt());
        return d;
    }

    public InscriptionEntity toEntity(Inscription d) {
        if (d == null) return null;
        InscriptionEntity e = new InscriptionEntity();
        e.setId(d.getId());
        e.setEleveId(d.getEleveId());
        e.setNiveauId(d.getNiveauId());
        e.setAnneeScolaireId(d.getAnneeScolaireId());
        e.setDateInscription(d.getDateInscription());
        e.setStatut(d.getStatut());
        e.setCommentaire(d.getCommentaire());
        e.setCreatedAt(d.getCreatedAt());
        e.setUpdatedAt(d.getUpdatedAt());
        return e;
    }
}
