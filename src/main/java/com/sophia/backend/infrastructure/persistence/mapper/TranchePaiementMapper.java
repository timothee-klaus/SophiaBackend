package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.TranchePaiementDTO;
import com.sophia.backend.domain.model.TranchePaiement;
import org.springframework.stereotype.Component;

@Component
public class TranchePaiementMapper {
    public TranchePaiementDTO toDto(TranchePaiement t) {
        if (t == null) return null;
        return new TranchePaiementDTO(t.getId(), t.getFraisScolaireId(), t.getNomTranche(), t.getMontant(), t.getDateLimiteDebut(), t.getDateLimiteFin(), t.getOrdre(), t.getCreatedAt());
    }

    public TranchePaiement toDomain(TranchePaiementDTO d) {
        if (d == null) return null;
        TranchePaiement t = new TranchePaiement();
        t.setId(d.getId());
        t.setFraisScolaireId(d.getFraisScolaireId());
        t.setNomTranche(d.getNomTranche());
        t.setMontant(d.getMontant());
        t.setDateLimiteDebut(d.getDateLimiteDebut());
        t.setDateLimiteFin(d.getDateLimiteFin());
        t.setOrdre(d.getOrdre());
        t.setCreatedAt(d.getCreatedAt());
        return t;
    }
}
