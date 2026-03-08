package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.TranchePaiementDTO;
import com.sophia.backend.domain.model.TranchePaiement;
import com.sophia.backend.infrastructure.persistence.entity.TranchePaiementEntity;
import org.springframework.stereotype.Component;

@Component
public class TranchePaiementMapper {
    public TranchePaiementDTO toDto(TranchePaiement t) {
        if (t == null) return null;
        TranchePaiementDTO dto = new TranchePaiementDTO();
        dto.setId(t.getId());
        dto.setFraisScolaireId(t.getFraisScolaireId());
        dto.setNomTranche(t.getNomTranche());
        dto.setMontant(t.getMontant());
        dto.setDateLimiteDebut(t.getDateLimiteDebut());
        dto.setDateLimiteFin(t.getDateLimiteFin());
        dto.setOrdre(t.getOrdre());
        dto.setCreatedAt(t.getCreatedAt());
        dto.setUpdatedAt(t.getUpdatedAt());
        return dto;
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
        // Ne pas mapper createdAt, updatedAt (READ_ONLY)
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
        t.setUpdatedAt(e.getUpdatedAt());
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
        e.setUpdatedAt(t.getUpdatedAt());
        return e;
    }
}
