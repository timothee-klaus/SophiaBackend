package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.TranchePaiementDTO;
import com.sophia.backend.domain.model.TranchePaiement;
import com.sophia.backend.infrastructure.persistence.entity.TranchePaiementEntity;
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

    public TranchePaiement toDomain(TranchePaiementEntity e) {
        if (e == null) return null;
        TranchePaiement t = new TranchePaiement();
        t.setId(e.getId());
        t.setFraisScolaireId(e.getFraisScolaireId());
        t.setNomTranche(e.getNomTranche());
        t.setMontant(e.getMontant());
        t.setDateLimiteDebut(e.getDateLimiteDebut());
        t.setDateLimiteFin(e.getDateLimiteFin());
        t.setOrdre(e.getOrdre());
        t.setCreatedAt(e.getCreatedAt());
        return t;
    }

    public TranchePaiementEntity toEntity(TranchePaiement t) {
        if (t == null) return null;
        TranchePaiementEntity e = new TranchePaiementEntity();
        e.setId(t.getId());
        e.setFraisScolaireId(t.getFraisScolaireId());
        e.setNomTranche(t.getNomTranche());
        e.setMontant(t.getMontant());
        e.setDateLimiteDebut(t.getDateLimiteDebut());
        e.setDateLimiteFin(t.getDateLimiteFin());
        e.setOrdre(t.getOrdre());
        e.setCreatedAt(t.getCreatedAt());
        return e;
    }
}

