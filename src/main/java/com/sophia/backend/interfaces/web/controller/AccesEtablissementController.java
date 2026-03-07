package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.AccesEtablissementDTO;
import com.sophia.backend.application.service.AccesEtablissementService;
import com.sophia.backend.domain.model.AccesEtablissement;
import com.sophia.backend.infrastructure.persistence.mapper.AccesEtablissementMapper;
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

@Tag(name = "Acces Etablissements", description = "Gestion des droits d'acces - Liaison utilisateur/etablissement")
@RestController
@RequestMapping("/api/v1/acces-etablissements")
@RequiredArgsConstructor
public class AccesEtablissementController {

    private final AccesEtablissementService service;
    private final AccesEtablissementMapper mapper;

    @Operation(summary = "Lister tous les acces", description = "Retourne tous les acces etablissement")
    @GetMapping
    public ResponseEntity<List<AccesEtablissementDTO>> getAll() {
        List<AccesEtablissementDTO> dtos = service.findByUtilisateurId(UUID.fromString("00000000-0000-0000-0000-000000000000")).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Recuperer un acces par ID", description = "Retourne les details d'un acces")
    @GetMapping("/{id}")
    public ResponseEntity<AccesEtablissementDTO> getById(@Parameter(description = "ID de l'acces") @PathVariable Long id) {
        return service.findById(id)
                .map(a -> ResponseEntity.ok(mapper.toDto(a)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Acces par utilisateur", description = "Liste les etablissements accessibles par un utilisateur")
    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<List<AccesEtablissementDTO>> getByUtilisateurId(@Parameter(description = "ID de l'utilisateur") @PathVariable UUID utilisateurId) {
        List<AccesEtablissementDTO> dtos = service.findByUtilisateurId(utilisateurId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Acces par etablissement", description = "Liste les utilisateurs ayant acces a un etablissement")
    @GetMapping("/etablissement/{etablissementId}")
    public ResponseEntity<List<AccesEtablissementDTO>> getByEtablissementId(@Parameter(description = "ID de l'etablissement") @PathVariable Long etablissementId) {
        List<AccesEtablissementDTO> dtos = service.findByEtablissementId(etablissementId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/accorder")
    public ResponseEntity<AccesEtablissementDTO> accorder(
            @RequestParam UUID utilisateurId,
            @RequestParam Long etablissementId) {
        AccesEtablissement acces = service.accorderAcces(utilisateurId, etablissementId);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(acces));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.revoquerAcces(id);
        return ResponseEntity.noContent().build();
    }
}


