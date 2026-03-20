package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.InscriptionDTO;
import com.sophia.backend.domain.model.Inscription;
import com.sophia.backend.infrastructure.persistence.entity.InscriptionEntity;
import org.springframework.stereotype.Component;

@Component
public class InscriptionMapper {
    public InscriptionDTO toDto(Inscription i) {
        if (i == null) return null;
        InscriptionDTO dto = new InscriptionDTO();
        dto.setUuid(i.getUuid());
        dto.setEleveUuid(i.getEleveUuid());
        dto.setNiveauUuid(i.getNiveauUuid());
        dto.setAnneeScolaireUuid(i.getAnneeScolaireUuid());
        dto.setDateInscription(i.getDateInscription());
        dto.setStatut(i.getStatut());
        dto.setCommentaire(i.getCommentaire());
        dto.setBloqueExamen(i.isBloqueExamen());
        dto.setBloqueEvaluation(i.isBloqueEvaluation());
        dto.setRaisonBlocage(i.getRaisonBlocage());
        dto.setCreatedAt(i.getCreatedAt());
        dto.setUpdatedAt(i.getUpdatedAt());
        return dto;
    }

    public Inscription toDomain(InscriptionDTO d) {
        if (d == null) return null;
        Inscription i = new Inscription();
        i.setUuid(d.getUuid());
        i.setEleveUuid(d.getEleveUuid());
        i.setNiveauUuid(d.getNiveauUuid());
        i.setAnneeScolaireUuid(d.getAnneeScolaireUuid());
        i.setDateInscription(d.getDateInscription());
        i.setStatut(d.getStatut());
        i.setCommentaire(d.getCommentaire());
        i.setBloqueExamen(d.isBloqueExamen());
        i.setBloqueEvaluation(d.isBloqueEvaluation());
        i.setRaisonBlocage(d.getRaisonBlocage());
        return i;
    }

    public Inscription toDomain(InscriptionEntity e) {
        if (e == null) return null;
        Inscription i = new Inscription();
        i.setId(e.getId());
        i.setUuid(e.getUuid());
        i.setEleveUuid(e.getEleveUuid());
        i.setNiveauUuid(e.getNiveauUuid());
        i.setAnneeScolaireUuid(e.getAnneeScolaireUuid());
        i.setDateInscription(e.getDateInscription());
        i.setStatut(e.getStatut());
        i.setCommentaire(e.getCommentaire());
        i.setBloqueExamen(e.isBloqueExamen());
        i.setBloqueEvaluation(e.isBloqueEvaluation());
        i.setRaisonBlocage(e.getRaisonBlocage());
        i.setCreatedAt(e.getCreatedAt());
        i.setUpdatedAt(e.getUpdatedAt());
        return i;
    }

    public InscriptionEntity toEntity(Inscription i) {
        if (i == null) return null;
        InscriptionEntity e = new InscriptionEntity();
        e.setId(i.getId());
        e.setUuid(i.getUuid());
        e.setEleveUuid(i.getEleveUuid());
        e.setNiveauUuid(i.getNiveauUuid());
        e.setAnneeScolaireUuid(i.getAnneeScolaireUuid());
        e.setDateInscription(i.getDateInscription());
        e.setStatut(i.getStatut());
        e.setCommentaire(i.getCommentaire());
        e.setBloqueExamen(i.isBloqueExamen());
        e.setBloqueEvaluation(i.isBloqueEvaluation());
        e.setRaisonBlocage(i.getRaisonBlocage());
        e.setCreatedAt(i.getCreatedAt());
        e.setUpdatedAt(i.getUpdatedAt());
        return e;
    }
}
