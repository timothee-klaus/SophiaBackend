package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.TranchePaiementDTO;
import com.sophia.backend.domain.model.TranchePaiement;
import com.sophia.backend.infrastructure.persistence.entity.TranchePaiementEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TranchePaiementMapper {
    public TranchePaiementDTO toDto(TranchePaiement t) {
        if (t == null) return null;
        TranchePaiementDTO dto = new TranchePaiementDTO();
        dto.setUuid(t.getUuid());
        dto.setFraisScolaireUuid(t.getFraisScolaireUuid());
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
        t.setUuid(d.getUuid());
        t.setFraisScolaireUuid(d.getFraisScolaireUuid());
        t.setNomTranche(d.getNomTranche());
        t.setMontant(d.getMontant());
        t.setDateLimiteDebut(d.getDateLimiteDebut());
        t.setDateLimiteFin(d.getDateLimiteFin());
        t.setOrdre(d.getOrdre());
        t.setCreatedAt(d.getCreatedAt());
        t.setUpdatedAt(d.getUpdatedAt());
        return t;
    }

    public TranchePaiement toDomain(TranchePaiementEntity e) {
        if (e == null) return null;
        TranchePaiement t = new TranchePaiement();
        t.setId(e.getId());
        t.setUuid(e.getUuid());
        t.setFraisScolaireId(e.getFraisScolaireId());
        t.setFraisScolaireUuid(e.getFraisScolaireUuid());
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
        e.setUuid(t.getUuid());
        e.setFraisScolaireId(t.getFraisScolaireId());
        e.setFraisScolaireUuid(t.getFraisScolaireUuid());
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
