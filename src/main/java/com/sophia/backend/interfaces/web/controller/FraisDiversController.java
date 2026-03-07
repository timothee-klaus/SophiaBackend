package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.FraisDiversDTO;
import com.sophia.backend.application.service.FraisDiversService;
import com.sophia.backend.domain.model.FraisDivers;
import com.sophia.backend.infrastructure.persistence.mapper.FraisDiversMapper;
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

@Tag(name = "Frais Divers", description = "Gestion des frais divers - Frais d'examen, activites, etc.")
@RestController
@RequestMapping("/api/v1/frais-divers")
@RequiredArgsConstructor
public class FraisDiversController {

    private final FraisDiversService service;
    private final FraisDiversMapper mapper;

    @Operation(summary = "Lister tous les frais divers", description = "Retourne tous les frais divers definis")
    @GetMapping
    public ResponseEntity<List<FraisDiversDTO>> getAll() {
        List<FraisDiversDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Recuperer un frais divers par ID", description = "Retourne les details d'un frais divers")
    @GetMapping("/{id}")
    public ResponseEntity<FraisDiversDTO> getById(@Parameter(description = "ID des frais divers") @PathVariable Long id) {
        return service.findById(id)
                .map(f -> ResponseEntity.ok(mapper.toDto(f)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Frais divers par niveau", description = "Liste les frais divers pour un niveau (ex: frais d'examen CM2)")
    @GetMapping("/niveau/{niveauId}")
    public ResponseEntity<List<FraisDiversDTO>> getByNiveauId(@Parameter(description = "ID du niveau") @PathVariable Long niveauId) {
        List<FraisDiversDTO> dtos = service.findByNiveauId(niveauId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Frais divers par annee scolaire", description = "Liste les frais divers pour une annee scolaire")
    @GetMapping("/annee-scolaire/{anneeScolaireId}")
    public ResponseEntity<List<FraisDiversDTO>> getByAnneeScolaireId(@Parameter(description = "ID de l'annee scolaire") @PathVariable Long anneeScolaireId) {
        List<FraisDiversDTO> dtos = service.findByAnneeScolaireId(anneeScolaireId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<FraisDiversDTO> create(@RequestBody FraisDiversDTO dto) {
        FraisDivers frais = mapper.toDomain(dto);
        FraisDivers saved = service.create(frais);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @PostMapping("/definir")
    public ResponseEntity<FraisDiversDTO> definir(
            @RequestParam Long niveauId,
            @RequestParam Long anneeScolaireId,
            @RequestBody FraisDiversDTO dto) {
        FraisDivers frais = mapper.toDomain(dto);
        FraisDivers saved = service.definirFraisDivers(niveauId, anneeScolaireId, frais);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @GetMapping("/niveau/{niveauId}/tous")
    public ResponseEntity<List<FraisDiversDTO>> obtenirFraisDiversPourNiveau(@PathVariable Long niveauId) {
        List<FraisDiversDTO> dtos = service.obtenirFraisDiversPourNiveau(niveauId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FraisDiversDTO> update(@PathVariable Long id, @RequestBody FraisDiversDTO dto) {
        FraisDivers frais = mapper.toDomain(dto);
        FraisDivers updated = service.update(frais);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}


