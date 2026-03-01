package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.DocumentDTO;
import com.sophia.backend.domain.model.Document;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapper {
    public DocumentDTO toDto(Document d) {
        if (d == null) return null;
        return new DocumentDTO(d.getId(), d.getEleveId(), d.getTypeDocument() != null ? d.getTypeDocument().name() : null, d.getNomFichier(), d.getCheminFichier(), d.getDateUpload(), d.getUtilisateurId(), d.getDescription(), d.getCreatedAt());
    }

    public Document toDomain(DocumentDTO dto) {
        if (dto == null) return null;
        Document d = new Document();
        d.setId(dto.getId());
        d.setEleveId(dto.getEleveId());
        d.setNomFichier(dto.getNomFichier());
        d.setCheminFichier(dto.getCheminFichier());
        d.setDateUpload(dto.getDateUpload());
        d.setUtilisateurId(dto.getUtilisateurId());
        d.setDescription(dto.getDescription());
        d.setCreatedAt(dto.getCreatedAt());
        return d;
    }
}
