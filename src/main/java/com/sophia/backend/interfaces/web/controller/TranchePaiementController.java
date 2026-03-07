package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.TranchePaiementDTO;
import com.sophia.backend.application.service.TranchePaiementService;
import com.sophia.backend.domain.model.TranchePaiement;
import com.sophia.backend.infrastructure.persistence.mapper.TranchePaiementMapper;
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

@Tag(name = "Tranches Paiement", description = "Gestion des tranches de paiement - Echeancier des frais scolaires")
@RestController
@RequestMapping("/api/v1/tranches-paiement")
@RequiredArgsConstructor
public class TranchePaiementController {

    private final TranchePaiementService service;
    private final TranchePaiementMapper mapper;

    @Operation(summary = "Lister toutes les tranches", description = "Retourne toutes les tranches de paiement")
    @GetMapping
    public ResponseEntity<List<TranchePaiementDTO>> getAll() {
        List<TranchePaiementDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Recuperer une tranche par ID", description = "Retourne les details d'une tranche")
    @GetMapping("/{id}")
    public ResponseEntity<TranchePaiementDTO> getById(@Parameter(description = "ID de la tranche") @PathVariable Long id) {
        return service.findById(id)
                .map(t -> ResponseEntity.ok(mapper.toDto(t)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Tranches par frais scolaire", description = "Liste les tranches d'un frais scolaire (1ere, 2eme, 3eme tranche...)")
    @GetMapping("/frais-scolaire/{fraisScolaireId}")
    public ResponseEntity<List<TranchePaiementDTO>> getByFraisScolaireId(@Parameter(description = "ID du frais scolaire") @PathVariable Long fraisScolaireId) {
        List<TranchePaiementDTO> dtos = service.findByFraisScolaireId(fraisScolaireId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Creer une tranche", description = "Cree une nouvelle tranche de paiement")
    @ApiResponse(responseCode = "201", description = "Tranche creee")
    @PostMapping
    public ResponseEntity<TranchePaiementDTO> create(@RequestBody TranchePaiementDTO dto) {
        TranchePaiement tranche = mapper.toDomain(dto);
        TranchePaiement saved = service.create(tranche);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @GetMapping("/frais-scolaire/{fraisScolaireId}/echeancier")
    public ResponseEntity<List<TranchePaiementDTO>> visualiserEcheancier(@PathVariable Long fraisScolaireId) {
        List<TranchePaiementDTO> dtos = service.visualiserEchéancierParFraisScolaire(fraisScolaireId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TranchePaiementDTO> update(@PathVariable Long id, @RequestBody TranchePaiementDTO dto) {
        TranchePaiement tranche = mapper.toDomain(dto);
        TranchePaiement updated = service.update(tranche);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}


