package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.EleveDTO;
import com.sophia.backend.application.service.EleveService;
import com.sophia.backend.domain.model.Eleve;
import com.sophia.backend.infrastructure.persistence.mapper.EleveMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Tag(name = "Eleves", description = "Gestion des dossiers eleves - Creation, modification, archivage et recherche des eleves")
@RestController
@RequestMapping("/api/v1/eleves")
@RequiredArgsConstructor
public class EleveController {

    private final EleveService service;
    private final EleveMapper mapper;

    @Operation(summary = "Créer un dossier élève", description = "Crée un nouveau dossier d'élève")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Dossier élève créé avec succès"),
        @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    public ResponseEntity<EleveDTO> create(@RequestBody EleveDTO dto) {
        Eleve eleve = mapper.toDomain(dto);
        Eleve saved = service.creerDossierEleve(eleve);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @Operation(summary = "Lister les élèves", description = "Retourne la liste de tous les élèves")
    @ApiResponse(responseCode = "200", description = "Liste des élèves récupérée avec succès")
    @GetMapping
    public ResponseEntity<List<EleveDTO>> getAll() {
        List<EleveDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Récupérer un élève", description = "Retourne les informations complètes d'un élève")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Élève trouvé"),
        @ApiResponse(responseCode = "404", description = "Élève non trouvé")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EleveDTO> getById(@Parameter(description = "ID unique de l'eleve (UUID)") @PathVariable UUID id) {
        return service.findById(id)
                .map(e -> ResponseEntity.ok(mapper.toDto(e)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Modifier un élève", description = "Modifie les informations d'un élève")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Élève modifié avec succès"),
        @ApiResponse(responseCode = "404", description = "Élève non trouvé")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EleveDTO> update(@Parameter(description = "ID de l'eleve") @PathVariable UUID id, @RequestBody EleveDTO dto) {
        Eleve eleve = mapper.toDomain(dto);
        try {
            Eleve updated = service.modifierEleve(id, eleve);
            return ResponseEntity.ok(mapper.toDto(updated));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Archiver un élève", description = "Archive un élève (départ, fin de cycle)")
    @ApiResponse(responseCode = "200", description = "Élève archivé avec succès")
    @PostMapping("/{id}/archiver")
    public ResponseEntity<EleveDTO> archiver(@Parameter(description = "ID de l'eleve a archiver") @PathVariable UUID id) {
        service.archiverEleve(id);
        return ResponseEntity.ok().build();
    }
}
