package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.PaiementDTO;
import com.sophia.backend.domain.model.Paiement;
import org.springframework.stereotype.Component;

@Component
public class PaiementMapper {
    public PaiementDTO toDto(Paiement p) {
        if (p == null) return null;
        return new PaiementDTO(
                p.getId(),
                p.getInscriptionId(),
                p.getTypePaiement() != null ? p.getTypePaiement().name() : null,
                p.getReferenceId(),
                p.getMontant(),
                p.getDatePaiement(),
                p.getModePaiement() != null ? p.getModePaiement().name() : null,
                p.getRecuPath(),
                p.getCommentaire(),
                p.getUtilisateurId(),
                p.getCreatedAt(),
                p.getUpdatedAt()
        );
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
        p.setCreatedAt(d.getCreatedAt());
        p.setUpdatedAt(d.getUpdatedAt());
        return p;
    }
}
