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
        d.setUuid(e.getUuid());
        d.setEleveUuid(e.getEleveUuid());
        d.setNiveauUuid(e.getNiveauUuid());
        d.setAnneeScolaireUuid(e.getAnneeScolaireUuid());
        d.setDateInscription(e.getDateInscription());
        d.setStatut(e.getStatut());
        d.setCommentaire(e.getCommentaire());
        d.setBloqueExamen(e.isBloqueExamen());
        d.setBloqueEvaluation(e.isBloqueEvaluation());
        d.setRaisonBlocage(e.getRaisonBlocage());
        d.setCreatedAt(e.getCreatedAt());
        d.setUpdatedAt(e.getUpdatedAt());
        return d;
    }

    public InscriptionEntity toEntity(Inscription d) {
        if (d == null) return null;
        InscriptionEntity e = new InscriptionEntity();
        e.setId(d.getId());
        e.setUuid(d.getUuid());
        e.setEleveUuid(d.getEleveUuid());
        e.setNiveauUuid(d.getNiveauUuid());
        e.setAnneeScolaireUuid(d.getAnneeScolaireUuid());
        e.setDateInscription(d.getDateInscription());
        e.setStatut(d.getStatut());
        e.setCommentaire(d.getCommentaire());
        e.setBloqueExamen(d.isBloqueExamen());
        e.setBloqueEvaluation(d.isBloqueEvaluation());
        e.setRaisonBlocage(d.getRaisonBlocage());
        e.setCreatedAt(d.getCreatedAt());
        e.setUpdatedAt(d.getUpdatedAt());
        return e;
    }
}
