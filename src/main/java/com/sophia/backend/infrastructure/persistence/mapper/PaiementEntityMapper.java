package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.domain.model.Paiement;
import com.sophia.backend.infrastructure.persistence.entity.PaiementEntity;
import org.springframework.stereotype.Component;

@Component
public class PaiementEntityMapper {
    public Paiement toDomain(PaiementEntity e) {
        if (e == null) return null;
        Paiement d = new Paiement();
        d.setId(e.getId());
        d.setInscriptionId(e.getInscriptionId());
        d.setTypePaiement(e.getTypePaiement());
        d.setReferenceId(e.getReferenceId());
        d.setMontant(e.getMontant());
        d.setDatePaiement(e.getDatePaiement());
        d.setModePaiement(e.getModePaiement());
        d.setRecuPath(e.getRecuPath());
        d.setCommentaire(e.getCommentaire());
        d.setUtilisateurId(e.getUtilisateurId());
        d.setCreatedAt(e.getCreatedAt());
        d.setUpdatedAt(e.getUpdatedAt());
        return d;
    }

    public PaiementEntity toEntity(Paiement d) {
        if (d == null) return null;
        PaiementEntity e = new PaiementEntity();
        e.setId(d.getId());
        e.setInscriptionId(d.getInscriptionId());
        e.setTypePaiement(d.getTypePaiement());
        e.setReferenceId(d.getReferenceId());
        e.setMontant(d.getMontant());
        e.setDatePaiement(d.getDatePaiement());
        e.setModePaiement(d.getModePaiement());
        e.setRecuPath(d.getRecuPath());
        e.setCommentaire(d.getCommentaire());
        e.setUtilisateurId(d.getUtilisateurId());
        e.setCreatedAt(d.getCreatedAt());
        e.setUpdatedAt(d.getUpdatedAt());
        return e;
    }
}
