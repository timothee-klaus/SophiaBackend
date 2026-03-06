package com.sophia.backend.infrastructure.persistence.mapper;

import com.sophia.backend.application.dto.DocumentDTO;
import com.sophia.backend.domain.model.Document;
import com.sophia.backend.domain.enums.TypeDocument;
import com.sophia.backend.infrastructure.persistence.entity.DocumentEntity;
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
        return e;
    }
}

