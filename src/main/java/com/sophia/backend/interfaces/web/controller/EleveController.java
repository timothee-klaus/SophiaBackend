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

    @Operation(summary = "Lister tous les eleves", description = "Retourne la liste complete de tous les eleves inscrits dans le systeme")
    @ApiResponse(responseCode = "200", description = "Liste des eleves recuperee avec succes")
    @GetMapping
    public ResponseEntity<List<EleveDTO>> getAll() {
        List<EleveDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Recuperer un eleve par ID", description = "Retourne les details complets d'un eleve specifique")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Eleve trouve"),
        @ApiResponse(responseCode = "404", description = "Eleve non trouve")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EleveDTO> getById(@Parameter(description = "ID unique de l'eleve (UUID)") @PathVariable UUID id) {
        return service.findById(id)
                .map(e -> ResponseEntity.ok(mapper.toDto(e)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Rechercher par matricule", description = "Recherche un eleve par son numero de matricule unique")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Eleve trouve"),
        @ApiResponse(responseCode = "404", description = "Aucun eleve avec cette matricule")
    })
    @GetMapping("/matricule/{matricule}")
    public ResponseEntity<EleveDTO> getByMatricule(@Parameter(description = "Matricule de l'eleve") @PathVariable String matricule) {
        return service.findByMatricule(matricule)
                .map(e -> ResponseEntity.ok(mapper.toDto(e)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Creer un nouveau dossier eleve", description = "Cree un nouveau dossier eleve avec toutes les informations personnelles et du tuteur")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Dossier eleve cree avec succes"),
        @ApiResponse(responseCode = "400", description = "Donnees invalides")
    })
    @PostMapping
    public ResponseEntity<EleveDTO> create(@RequestBody EleveDTO dto) {
        Eleve eleve = mapper.toDomain(dto);
        Eleve saved = service.creerDossierEleve(eleve);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @Operation(summary = "Modifier un eleve", description = "Met a jour les informations d'un eleve existant")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Eleve modifie avec succes"),
        @ApiResponse(responseCode = "404", description = "Eleve non trouve")
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

    @Operation(summary = "Supprimer un eleve", description = "Supprime definitivement un dossier eleve du systeme")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Eleve supprime"),
        @ApiResponse(responseCode = "404", description = "Eleve non trouve")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Parameter(description = "ID de l'eleve") @PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Archiver un eleve", description = "Archive un eleve (depart, fin de cycle) sans le supprimer")
    @ApiResponse(responseCode = "200", description = "Eleve archive avec succes")
    @PostMapping("/{id}/archiver")
    public ResponseEntity<Void> archiver(@Parameter(description = "ID de l'eleve a archiver") @PathVariable UUID id) {
        service.archiverEleve(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Rechercher un eleve", description = "Recherche detaillee d'un eleve avec toutes ses informations")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Eleve trouve"),
        @ApiResponse(responseCode = "404", description = "Eleve non trouve")
    })
    @GetMapping("/{id}/rechercher")
    public ResponseEntity<EleveDTO> rechercher(@Parameter(description = "ID de l'eleve") @PathVariable UUID id) {
        return service.rechercherEleve(id)
                .map(e -> ResponseEntity.ok(mapper.toDto(e)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

