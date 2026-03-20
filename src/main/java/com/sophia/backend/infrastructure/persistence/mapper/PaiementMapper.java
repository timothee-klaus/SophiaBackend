package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.PaiementDTO;
import com.sophia.backend.domain.model.Paiement;
import org.springframework.stereotype.Component;

@Component
public class PaiementMapper {
    public PaiementDTO toDto(Paiement p) {
        if (p == null) return null;
        PaiementDTO dto = new PaiementDTO();
        dto.setUuid(p.getUuid());
        dto.setInscriptionUuid(p.getInscriptionUuid());
        dto.setTypePaiement(p.getTypePaiement());
        dto.setReferenceUuid(p.getReferenceUuid());
        dto.setMontant(p.getMontant());
        dto.setDatePaiement(p.getDatePaiement());
        dto.setModePaiement(p.getModePaiement());
        dto.setCommentaire(p.getCommentaire());
        dto.setCreatedAt(p.getCreatedAt());
        dto.setUpdatedAt(p.getUpdatedAt());
        return dto;
    }

    public Paiement toDomain(PaiementDTO d) {
        if (d == null) return null;
        Paiement p = new Paiement();
        p.setUuid(d.getUuid());
        p.setInscriptionUuid(d.getInscriptionUuid());
        p.setTypePaiement(d.getTypePaiement());
        p.setReferenceUuid(d.getReferenceUuid());
        p.setMontant(d.getMontant());
        p.setDatePaiement(d.getDatePaiement());
        p.setModePaiement(d.getModePaiement());
        p.setCommentaire(d.getCommentaire());
        return p;
    }
}
