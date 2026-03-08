package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.EtablissementDTO;
import com.sophia.backend.application.service.EtablissementService;
import com.sophia.backend.domain.model.Etablissement;
import com.sophia.backend.infrastructure.persistence.mapper.EtablissementMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Établissements", description = "Gestion des établissements scolaires")
@RestController
@RequestMapping("/api/v1/etablissements")
@RequiredArgsConstructor
public class EtablissementController {

    private final EtablissementService service;
    private final EtablissementMapper mapper;

    @Operation(
        summary = "Lister tous les établissements",
        description = "Retourne la liste de tous les établissements du système"
    )
    @ApiResponse(responseCode = "200", description = "Liste des établissements récupérée avec succès")
    @GetMapping
    public ResponseEntity<List<EtablissementDTO>> getAll() {
        List<EtablissementDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(
        summary = "Récupérer un établissement",
        description = "Retourne les détails complets d'un établissement spécifique"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Établissement trouvé"),
        @ApiResponse(responseCode = "404", description = "Établissement non trouvé")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EtablissementDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(e -> ResponseEntity.ok(mapper.toDto(e)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
        summary = "Créer un établissement",
        description = "Crée un nouvel établissement scolaire (ex: Institut Sophia, Groupe Scolaire La Colombe)"
    )
    @ApiResponse(responseCode = "201", description = "Établissement créé avec succès")
    @PostMapping
    public ResponseEntity<EtablissementDTO> create(@RequestBody EtablissementDTO dto) {
        Etablissement etablissement = mapper.toDomain(dto);
        Etablissement saved = service.creerEtablissement(etablissement);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @Operation(
        summary = "Modifier un établissement",
        description = "Modifie les informations d'un établissement existant"
    )
    @ApiResponse(responseCode = "200", description = "Établissement modifié avec succès")
    @PutMapping("/{id}")
    public ResponseEntity<EtablissementDTO> update(@PathVariable Long id, @RequestBody EtablissementDTO dto) {
        Etablissement etablissement = mapper.toDomain(dto);
        try {
            Etablissement updated = service.modifierEtablissement(id, etablissement);
            return ResponseEntity.ok(mapper.toDto(updated));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
        summary = "Supprimer un établissement",
        description = "Supprime un établissement du système"
    )
    @ApiResponse(responseCode = "204", description = "Établissement supprimé avec succès")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.supprimerEtablissement(id);
        return ResponseEntity.noContent().build();
    }
}
