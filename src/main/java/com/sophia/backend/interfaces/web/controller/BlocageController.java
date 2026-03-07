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

    @Operation(summary = "Lister tous les blocages", description = "Retourne la liste de tous les blocages du systeme")
    @ApiResponse(responseCode = "200", description = "Liste des blocages")
    @GetMapping
    public ResponseEntity<List<BlocageDTO>> getAll() {
        List<BlocageDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Recuperer un blocage par ID", description = "Retourne les details d'un blocage")
    @GetMapping("/{id}")
    public ResponseEntity<BlocageDTO> getById(@Parameter(description = "ID du blocage") @PathVariable Long id) {
        return service.findById(id)
                .map(b -> ResponseEntity.ok(mapper.toDto(b)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Blocages d'une inscription", description = "Liste tous les blocages appliques a une inscription")
    @GetMapping("/inscription/{inscriptionId}")
    public ResponseEntity<List<BlocageDTO>> getByInscriptionId(@Parameter(description = "ID de l'inscription") @PathVariable Long inscriptionId) {
        List<BlocageDTO> dtos = service.findByInscriptionId(inscriptionId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Blocages actifs", description = "Liste tous les blocages actuellement actifs")
    @GetMapping("/actifs")
    public ResponseEntity<List<BlocageDTO>> getActiveBlocks() {
        List<BlocageDTO> dtos = service.findActiveBlocks().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Bloquer une inscription", description = "Marque un eleve comme non autorise a composer (EXAMEN ou EVALUATION)")
    @ApiResponse(responseCode = "201", description = "Blocage cree avec succes")
    @PostMapping("/bloquer")
    public ResponseEntity<BlocageDTO> bloquer(
            @Parameter(description = "ID de l'inscription") @RequestParam Long inscriptionId,
            @Parameter(description = "Type: EXAMEN ou EVALUATION") @RequestParam String typeBlocage,
            @Parameter(description = "Raison du blocage") @RequestParam String raison) {
        Blocage blocage = service.bloquerInscription(inscriptionId, typeBlocage, raison);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(blocage));
    }

    @PostMapping("/{id}/lever")
    public ResponseEntity<Void> lever(@PathVariable Long id, @RequestParam UUID utilisateurId) {
        service.leverBlocage(id, utilisateurId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/inscription/{inscriptionId}/est-bloquee")
    public ResponseEntity<Boolean> estBloquee(@PathVariable Long inscriptionId) {
        boolean bloquee = service.estBloquee(inscriptionId);
        return ResponseEntity.ok(bloquee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}


