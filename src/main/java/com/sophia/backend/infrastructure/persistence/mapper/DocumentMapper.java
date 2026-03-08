package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.DocumentDTO;
import com.sophia.backend.domain.model.Document;
import com.sophia.backend.infrastructure.persistence.entity.DocumentEntity;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapper {
    public DocumentDTO toDto(Document d) {
        if (d == null) return null;
        DocumentDTO dto = new DocumentDTO();
        dto.setId(d.getId());
        dto.setEleveId(d.getEleveId());
        dto.setTypeDocument(d.getTypeDocument() != null ? d.getTypeDocument().name() : null);
        dto.setNomFichier(d.getNomFichier());
        dto.setCheminFichier(d.getCheminFichier());
        dto.setDateUpload(d.getDateUpload());
        dto.setUtilisateurId(d.getUtilisateurId());
        dto.setDescription(d.getDescription());
        dto.setCreatedAt(d.getCreatedAt());
        dto.setUpdatedAt(d.getUpdatedAt());
        return dto;
    }

    public Document toDomain(DocumentDTO d) {
        if (d == null) return null;
        Document doc = new Document();
        doc.setId(d.getId());
        doc.setEleveId(d.getEleveId());
        if (d.getTypeDocument() != null) {
            try {
                doc.setTypeDocument(com.sophia.backend.domain.enums.TypeDocument.valueOf(d.getTypeDocument()));
            } catch (IllegalArgumentException ex) {}
        }
        doc.setNomFichier(d.getNomFichier());
        doc.setCheminFichier(d.getCheminFichier());
        doc.setDateUpload(d.getDateUpload());
        doc.setUtilisateurId(d.getUtilisateurId());
        doc.setDescription(d.getDescription());
        // Ne pas mapper createdAt, updatedAt (READ_ONLY)
        return doc;
    }

    public Document toDomain(DocumentEntity e) {
        if (e == null) return null;
        Document d = new Document();
        d.setId(e.getId());
        d.setEleveId(e.getEleveId());
        d.setTypeDocument(e.getTypeDocument());
        d.setNomFichier(e.getNomFichier());
        d.setCheminFichier(e.getCheminFichier());
        d.setDateUpload(e.getDateUpload());
        d.setUtilisateurId(e.getUtilisateurId());
        d.setDescription(e.getDescription());
        d.setCreatedAt(e.getCreatedAt());
        d.setUpdatedAt(e.getUpdatedAt());
        return d;
    }

    public DocumentEntity toEntity(Document d) {
        if (d == null) return null;
        DocumentEntity e = new DocumentEntity();
        e.setId(d.getId());
        e.setEleveId(d.getEleveId());
        e.setTypeDocument(d.getTypeDocument());
        e.setNomFichier(d.getNomFichier());
        e.setCheminFichier(d.getCheminFichier());
        e.setDateUpload(d.getDateUpload());
        e.setUtilisateurId(d.getUtilisateurId());
        e.setDescription(d.getDescription());
        e.setCreatedAt(d.getCreatedAt());
        e.setUpdatedAt(d.getUpdatedAt());
        return e;
    }
}
