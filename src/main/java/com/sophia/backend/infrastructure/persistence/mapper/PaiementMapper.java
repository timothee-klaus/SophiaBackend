package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.PaiementDTO;
import com.sophia.backend.domain.model.Paiement;
import org.springframework.stereotype.Component;

@Component
public class PaiementMapper {
    public PaiementDTO toDto(Paiement p) {
        if (p == null) return null;
        PaiementDTO dto = new PaiementDTO();
        dto.setId(p.getId());
        dto.setInscriptionId(p.getInscriptionId());
        dto.setTypePaiement(p.getTypePaiement() != null ? p.getTypePaiement().name() : null);
        dto.setReferenceId(p.getReferenceId());
        dto.setMontant(p.getMontant());
        dto.setDatePaiement(p.getDatePaiement());
        dto.setModePaiement(p.getModePaiement() != null ? p.getModePaiement().name() : null);
        dto.setRecuPath(p.getRecuPath());
        dto.setCommentaire(p.getCommentaire());
        dto.setUtilisateurId(p.getUtilisateurId());
        dto.setCreatedAt(p.getCreatedAt());
        dto.setUpdatedAt(p.getUpdatedAt());
        return dto;
    }

    public Paiement toDomain(PaiementDTO d) {
        if (d == null) return null;
        Paiement p = new Paiement();
        p.setId(d.getId());
        p.setInscriptionId(d.getInscriptionId());
        p.setReferenceId(d.getReferenceId());
        p.setMontant(d.getMontant());
        p.setDatePaiement(d.getDatePaiement());
        p.setRecuPath(d.getRecuPath());
        p.setCommentaire(d.getCommentaire());
        p.setUtilisateurId(d.getUtilisateurId());
        // Ne pas mapper createdAt, updatedAt (READ_ONLY)
        return p;
    }
}
