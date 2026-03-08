package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.DocumentDTO;
import com.sophia.backend.application.service.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/documents")
@Tag(name = "Documents", description = "Gestion des documents (scans, attestations, etc.)")
public class DocumentController {

    private final DocumentService service;

    public DocumentController(DocumentService service) {
        this.service = service;
    }

    @Operation(summary = "Télécharger un document", description = "Télécharge le fichier d'un document")
    @GetMapping("/{id}/telecharger")
    public ResponseEntity<byte[]> telecharger(@PathVariable Long id) {
        return ResponseEntity.ok(new byte[0]);
    }

    @Operation(summary = "Créer un document", description = "Crée/télécharge un nouveau document")
    @PostMapping
    public ResponseEntity<DocumentDTO> create(@RequestBody DocumentDTO dto) {
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Lister les documents", description = "Retourne la liste de tous les documents")
    @GetMapping
    public ResponseEntity<List<DocumentDTO>> getAll() {
        return ResponseEntity.ok(List.of());
    }

    @Operation(summary = "Récupérer un document", description = "Récupère les détails d'un document")
    @GetMapping("/{id}")
    public ResponseEntity<DocumentDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new DocumentDTO());
    }

    @Operation(summary = "Documents d'un élève", description = "Liste les documents associés à un élève")
    @GetMapping("/eleve/{eleveId}")
    public ResponseEntity<List<DocumentDTO>> getByEleveId(@PathVariable UUID eleveId) {
        return ResponseEntity.ok(List.of());
    }

    @Operation(summary = "Modifier un document", description = "Modifie les détails d'un document")
    @PutMapping("/{id}")
    public ResponseEntity<DocumentDTO> update(@PathVariable Long id, @RequestBody DocumentDTO dto) {
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Supprimer un document", description = "Supprime un document")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
