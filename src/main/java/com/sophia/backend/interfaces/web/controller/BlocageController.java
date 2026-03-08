package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.BlocageDTO;
import com.sophia.backend.application.service.BlocageService;
import com.sophia.backend.domain.model.Blocage;
import com.sophia.backend.infrastructure.persistence.mapper.BlocageMapper;
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

@Tag(name = "Blocages", description = "Gestion des blocages - Bloquer/debloquer un eleve pour les examens ou evaluations")
@RestController
@RequestMapping("/api/v1/blocages")
@RequiredArgsConstructor
public class BlocageController {

    private final BlocageService service;
    private final BlocageMapper mapper;

    @Operation(summary = "Créer un blocage", description = "Marque un élève comme non autorisé à composer si conditions non remplies (ex: deux premières tranches non payées)")
    @ApiResponse(responseCode = "201", description = "Blocage cree avec succes")
    @PostMapping
    public ResponseEntity<BlocageDTO> create(@RequestBody BlocageDTO dto) {
        Blocage blocage = service.bloquerInscription(dto.getInscriptionId(), dto.getTypeBlocage(), dto.getRaison());
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(blocage));
    }

    @Operation(summary = "Lister les blocages", description = "Retourne la liste de tous les blocages")
    @ApiResponse(responseCode = "200", description = "Liste des blocages")
    @GetMapping
    public ResponseEntity<List<BlocageDTO>> getAll() {
        List<BlocageDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Récupérer un blocage", description = "Retourne les détails d'un blocage spécifique")
    @GetMapping("/{id}")
    public ResponseEntity<BlocageDTO> getById(@Parameter(description = "ID du blocage") @PathVariable Long id) {
        return service.findById(id)
                .map(b -> ResponseEntity.ok(mapper.toDto(b)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Lever un blocage", description = "Lève le blocage après régularisation du paiement")
    @PostMapping("/{id}/lever")
    public ResponseEntity<BlocageDTO> leverBlocage(@PathVariable Long id) {
        service.leverBlocage(id, null);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Supprimer un blocage", description = "Supprime un blocage")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
