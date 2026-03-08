package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.InscriptionDTO;
import com.sophia.backend.domain.model.Inscription;
import com.sophia.backend.domain.enums.StatutInscription;
import org.springframework.stereotype.Component;

@Component
public class InscriptionMapper {
    public InscriptionDTO toDto(Inscription i) {
        if (i == null) return null;
        InscriptionDTO dto = new InscriptionDTO();
        dto.setId(i.getId());
        dto.setEleveId(i.getEleveId());
        dto.setNiveauId(i.getNiveauId());
        dto.setAnneeScolaireId(i.getAnneeScolaireId());
        dto.setDateInscription(i.getDateInscription());
        dto.setStatut(i.getStatut() != null ? i.getStatut().name() : null);
        dto.setCommentaire(i.getCommentaire());
        dto.setCreatedAt(i.getCreatedAt());
        dto.setUpdatedAt(i.getUpdatedAt());
        return dto;
    }

    public Inscription toDomain(InscriptionDTO d) {
        if (d == null) return null;
        Inscription i = new Inscription();
        i.setId(d.getId());
        i.setEleveId(d.getEleveId());
        i.setNiveauId(d.getNiveauId());
        i.setAnneeScolaireId(d.getAnneeScolaireId());
        i.setDateInscription(d.getDateInscription());
        if (d.getStatut() != null) {
            try {
                i.setStatut(StatutInscription.valueOf(d.getStatut()));
            } catch (IllegalArgumentException ex) {}
        }
        i.setCommentaire(d.getCommentaire());
        // Ne pas mapper createdAt, updatedAt (READ_ONLY)
        return i;
    }
}
