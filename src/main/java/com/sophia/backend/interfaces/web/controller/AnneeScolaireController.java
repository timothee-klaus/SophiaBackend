package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.AnneeScolaireDTO;
import com.sophia.backend.application.service.AnneeScolaireService;
import com.sophia.backend.domain.model.AnneeScolaire;
import com.sophia.backend.infrastructure.persistence.mapper.AnneeScolaireMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Annees Scolaires", description = "Gestion des annees scolaires - Ouverture, cloture, activation")
@RestController
@RequestMapping("/api/v1/annees-scolaires")
@RequiredArgsConstructor
public class AnneeScolaireController {

    private final AnneeScolaireService service;
    private final AnneeScolaireMapper mapper;

    @Operation(summary = "Lister toutes les annees scolaires", description = "Retourne toutes les annees scolaires")
    @GetMapping
    public ResponseEntity<List<AnneeScolaireDTO>> getAll() {
        List<AnneeScolaireDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Recuperer une annee scolaire par ID", description = "Retourne les details d'une annee scolaire")
    @GetMapping("/{id}")
    public ResponseEntity<AnneeScolaireDTO> getById(@Parameter(description = "ID de l'annee scolaire") @PathVariable Long id) {
        return service.findById(id)
                .map(a -> ResponseEntity.ok(mapper.toDto(a)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Annee scolaire active", description = "Retourne l'annee scolaire actuellement active")
    @GetMapping("/active")
    public ResponseEntity<AnneeScolaireDTO> getActive() {
        return service.obtenirAnneeScolaireActive()
                .map(a -> ResponseEntity.ok(mapper.toDto(a)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Creer une annee scolaire", description = "Cree une nouvelle annee scolaire (ex: 2024-2025)")
    @ApiResponse(responseCode = "201", description = "Annee scolaire creee")
    @PostMapping
    public ResponseEntity<AnneeScolaireDTO> create(@RequestBody AnneeScolaireDTO dto) {
        AnneeScolaire annee = mapper.toDomain(dto);
        AnneeScolaire saved = service.create(annee);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @PostMapping("/ouvrir")
    public ResponseEntity<AnneeScolaireDTO> ouvrir(@RequestBody AnneeScolaireDTO dto) {
        AnneeScolaire annee = mapper.toDomain(dto);
        AnneeScolaire opened = service.ouvrirAnneeScolaire(annee);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(opened));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnneeScolaireDTO> update(@PathVariable Long id, @RequestBody AnneeScolaireDTO dto) {
        AnneeScolaire annee = mapper.toDomain(dto);
        AnneeScolaire updated = service.update(annee);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/cloturer")
    public ResponseEntity<Void> cloturer(@PathVariable Long id) {
        service.cloturerAnneeScolaire(id);
        return ResponseEntity.ok().build();
    }
}


