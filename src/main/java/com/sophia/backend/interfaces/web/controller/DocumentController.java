package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.DocumentDTO;
import com.sophia.backend.application.service.DocumentService;
import com.sophia.backend.domain.model.Document;
import com.sophia.backend.infrastructure.persistence.mapper.DocumentMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Tag(name = "Documents", description = "Gestion des documents - Bulletins, attestations, scans et pieces justificatives")
@RestController
@RequestMapping("/api/v1/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService service;
    private final DocumentMapper mapper;

    @Operation(summary = "Lister tous les documents", description = "Retourne tous les documents du systeme")
    @GetMapping
    public ResponseEntity<List<DocumentDTO>> getAll() {
        List<DocumentDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Recuperer un document par ID", description = "Retourne les details d'un document")
    @GetMapping("/{id}")
    public ResponseEntity<DocumentDTO> getById(@Parameter(description = "ID du document") @PathVariable Long id) {
        return service.findById(id)
                .map(d -> ResponseEntity.ok(mapper.toDto(d)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Documents d'un eleve", description = "Liste tous les documents associes a un eleve")
    @GetMapping("/eleve/{eleveId}")
    public ResponseEntity<List<DocumentDTO>> getByEleveId(@Parameter(description = "ID de l'eleve") @PathVariable UUID eleveId) {
        List<DocumentDTO> dtos = service.findByEleveId(eleveId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Televerser un document", description = "Enregistre un nouveau document pour un eleve")
    @ApiResponse(responseCode = "201", description = "Document enregistre")
    @PostMapping
    public ResponseEntity<DocumentDTO> create(@RequestBody DocumentDTO dto) {
        Document document = mapper.toDomain(dto);
        Document saved = service.create(document);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @PostMapping("/televerser")
    public ResponseEntity<DocumentDTO> televerser(
            @RequestParam UUID eleveId,
            @RequestParam UUID utilisateurId,
            @RequestBody DocumentDTO dto) {
        Document document = mapper.toDomain(dto);
        Document saved = service.televersRecu(eleveId, utilisateurId, document);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @GetMapping("/eleve/{eleveId}/tous")
    public ResponseEntity<List<DocumentDTO>> obtenirDocumentsEleve(@PathVariable UUID eleveId) {
        List<DocumentDTO> dtos = service.obtenirDocumentsEleve(eleveId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentDTO> update(@PathVariable Long id, @RequestBody DocumentDTO dto) {
        Document document = mapper.toDomain(dto);
        Document updated = service.update(document);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}


