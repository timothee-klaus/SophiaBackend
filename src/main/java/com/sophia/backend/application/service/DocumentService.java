package com.sophia.backend.application.service;
import com.sophia.backend.domain.model.Document;
import com.sophia.backend.domain.repository.DocumentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class DocumentService {
    private final DocumentRepository documentRepository;
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }
    public Optional<Document> findById(Long id) {
        return documentRepository.findById(id);
    }
    public List<Document> findAll() {
        return documentRepository.findAll();
    }
    public List<Document> findByEleveId(UUID eleveId) {
        return documentRepository.findByEleveId(eleveId);
    }
    public Document create(Document document) {
        return documentRepository.save(document);
    }
    public Document update(Document document) {
        return documentRepository.save(document);
    }
    public void delete(Long id) {
        documentRepository.deleteById(id);
    }
    public Document televersRecu(UUID eleveId, UUID utilisateurId, Document document) {
        document.setEleveId(eleveId);
        document.setUtilisateurId(utilisateurId);
        return this.create(document);
    }
    public List<Document> obtenirDocumentsEleve(UUID eleveId) {
        return this.findByEleveId(eleveId);
    }
}
