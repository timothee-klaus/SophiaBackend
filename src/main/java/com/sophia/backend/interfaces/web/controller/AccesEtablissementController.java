package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.AccesEtablissementDTO;
import com.sophia.backend.application.service.AccesEtablissementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/acces-etablissement")
@Tag(name = "Accès Établissements", description = "Gestion des accès utilisateurs aux établissements")
public class AccesEtablissementController {

    private final AccesEtablissementService service;

    public AccesEtablissementController(AccesEtablissementService service) {
        this.service = service;
    }

    @Operation(summary = "Accorder accès", description = "Accorde un accès à un utilisateur pour un établissement")
    @PostMapping
    public ResponseEntity<AccesEtablissementDTO> create(@RequestBody AccesEtablissementDTO dto) {
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Récupérer un accès", description = "Récupère les détails d'un accès établissement")
    @GetMapping("/{id}")
    public ResponseEntity<AccesEtablissementDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new AccesEtablissementDTO());
    }

    @Operation(summary = "Lister les accès", description = "Liste tous les accès établissements")
    @GetMapping
    public ResponseEntity<List<AccesEtablissementDTO>> getAll() {
        return ResponseEntity.ok(List.of());
    }

    @Operation(summary = "Établissements d'un utilisateur", description = "Liste les établissements auxquels un utilisateur a accès")
    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<List<AccesEtablissementDTO>> getByUtilisateur(@PathVariable UUID utilisateurId) {
        return ResponseEntity.ok(List.of());
    }

    @Operation(summary = "Utilisateurs d'un établissement", description = "Liste les utilisateurs ayant accès à un établissement")
    @GetMapping("/etablissement/{etablissementId}")
    public ResponseEntity<List<AccesEtablissementDTO>> getByEtablissement(@PathVariable Long etablissementId) {
        return ResponseEntity.ok(List.of());
    }

    @Operation(summary = "Révoquer l'accès", description = "Révoque l'accès d'un utilisateur à un établissement")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
