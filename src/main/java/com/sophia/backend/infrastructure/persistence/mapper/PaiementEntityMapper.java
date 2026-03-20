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
        d.setUuid(e.getUuid());
        d.setInscriptionUuid(e.getInscriptionUuid());
        d.setTypePaiement(e.getTypePaiement());
        d.setReferenceUuid(e.getReferenceUuid());
        d.setMontant(e.getMontant());
        d.setDatePaiement(e.getDatePaiement());
        d.setModePaiement(e.getModePaiement());
        d.setCommentaire(e.getCommentaire());
        d.setCreatedAt(e.getCreatedAt());
        d.setUpdatedAt(e.getUpdatedAt());
        return d;
    }

    public PaiementEntity toEntity(Paiement d) {
        if (d == null) return null;
        PaiementEntity e = new PaiementEntity();
        e.setId(d.getId());
        e.setUuid(d.getUuid());
        e.setInscriptionUuid(d.getInscriptionUuid());
        e.setTypePaiement(d.getTypePaiement());
        e.setReferenceUuid(d.getReferenceUuid());
        e.setMontant(d.getMontant());
        e.setDatePaiement(d.getDatePaiement());
        e.setModePaiement(d.getModePaiement());
        e.setCommentaire(d.getCommentaire());
        e.setCreatedAt(d.getCreatedAt());
        e.setUpdatedAt(d.getUpdatedAt());
        return e;
    }
}
