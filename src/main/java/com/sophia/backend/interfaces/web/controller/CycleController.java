package com.sophia.backend.interfaces.web.controller;
import com.sophia.backend.application.dto.CycleDTO;
import com.sophia.backend.application.service.CycleService;
import com.sophia.backend.domain.model.Cycle;
import com.sophia.backend.infrastructure.persistence.mapper.CycleMapper;
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
@Tag(name = "Cycles", description = "Gestion des cycles scolaires - Prescolaire, Primaire, College, Lycee")
@RestController
@RequestMapping("/api/v1/cycles")
@RequiredArgsConstructor
public class CycleController {
    private final CycleService service;
    private final CycleMapper mapper;
    @Operation(
        summary = "Créer un cycle",
        description = "Crée un nouveau cycle scolaire (ex: Préscolaire, Primaire, Collège, Lycée). Le UID est généré automatiquement."
    )
    @ApiResponse(responseCode = "201", description = "Cycle créé avec succès")
    @PostMapping
    public ResponseEntity<CycleDTO> create(@RequestBody CycleDTO dto) {
        dto.setUuid(null);
        Cycle cycle = mapper.toDomain(dto);
        Cycle saved = service.create(cycle);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }
    @Operation(summary = "Lister les cycles", description = "Retourne la liste de tous les cycles disponibles")
    @ApiResponse(responseCode = "200", description = "Liste des cycles récupérée")
    @GetMapping
    public ResponseEntity<List<CycleDTO>> getAll() {
        List<CycleDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
    @Operation(summary = "Récupérer un cycle", description = "Retourne les détails d'un cycle spécifique par son UID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cycle trouvé"),
        @ApiResponse(responseCode = "404", description = "Cycle non trouvé")
    })
    @GetMapping("/{uid}")
    public ResponseEntity<CycleDTO> getByUid(@Parameter(description = "UID unique du cycle (UUID)") @PathVariable UUID uid) {
        return service.findByUid(uid)
                .map(c -> ResponseEntity.ok(mapper.toDto(c)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Modifier un cycle", description = "Modifie les informations d'un cycle")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cycle modifié avec succès"),
        @ApiResponse(responseCode = "404", description = "Cycle non trouvé")
    })
    @PutMapping("/{uid}")
    public ResponseEntity<CycleDTO> update(
            @Parameter(description = "UID du cycle") @PathVariable UUID uid,
            @RequestBody CycleDTO dto) {
        try {
            Cycle cycle = mapper.toDomain(dto);
            Cycle updated = service.update(uid, cycle);
            return ResponseEntity.ok(mapper.toDto(updated));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "Supprimer un cycle", description = "Supprime un cycle du système")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Cycle supprimé avec succès"),
        @ApiResponse(responseCode = "404", description = "Cycle non trouvé")
    })
    @DeleteMapping("/{uid}")
    public ResponseEntity<Void> delete(@Parameter(description = "UID du cycle") @PathVariable UUID uid) {
        try {
            service.deleteByUid(uid);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
