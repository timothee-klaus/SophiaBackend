package com.sophia.backend.domain.repository;

import com.sophia.backend.domain.model.Document;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DocumentRepository {
    Optional<Document> findById(Long id);
    List<Document> findAll();
    List<Document> findByEleveId(UUID eleveId);
    Document save(Document document);
    void deleteById(Long id);
}

