package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.InscriptionDTO;
import com.sophia.backend.application.service.InscriptionService;
import com.sophia.backend.domain.model.Inscription;
import com.sophia.backend.infrastructure.persistence.mapper.InscriptionMapper;
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

@Tag(name = "Inscriptions", description = "Gestion des inscriptions scolaires - Depot de dossier, validation des pieces, suivi des statuts")
@RestController
@RequestMapping("/api/v1/inscriptions")
@RequiredArgsConstructor
public class InscriptionController {

    private final InscriptionService service;
    private final InscriptionMapper mapper;

    @Operation(summary = "Lister toutes les inscriptions", description = "Retourne la liste de toutes les inscriptions du systeme")
    @ApiResponse(responseCode = "200", description = "Liste des inscriptions recuperee")
    @GetMapping
    public ResponseEntity<List<InscriptionDTO>> getAll() {
        List<InscriptionDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Recuperer une inscription par ID", description = "Retourne les details d'une inscription specifique")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Inscription trouvee"),
        @ApiResponse(responseCode = "404", description = "Inscription non trouvee")
    })
    @GetMapping("/{id}")
    public ResponseEntity<InscriptionDTO> getById(@Parameter(description = "ID de l'inscription") @PathVariable Long id) {
        return service.findById(id)
                .map(i -> ResponseEntity.ok(mapper.toDto(i)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Inscriptions par eleve", description = "Retourne toutes les inscriptions d'un eleve specifique")
    @ApiResponse(responseCode = "200", description = "Liste des inscriptions de l'eleve")
    @GetMapping("/eleve/{eleveId}")
    public ResponseEntity<List<InscriptionDTO>> getByEleveId(@Parameter(description = "ID de l'eleve") @PathVariable UUID eleveId) {
        List<InscriptionDTO> dtos = service.findByEleveId(eleveId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Enregistrer une nouvelle inscription", description = "Cree un nouveau dossier d'inscription pour un eleve dans un niveau et une annee scolaire")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Inscription creee avec succes"),
        @ApiResponse(responseCode = "400", description = "Donnees invalides")
    })
    @PostMapping
    public ResponseEntity<InscriptionDTO> create(@RequestBody InscriptionDTO dto) {
        Inscription inscription = mapper.toDomain(dto);
        Inscription saved = service.enregistrerDossierInscription(inscription);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @Operation(summary = "Valider les pieces du dossier", description = "Valide les pieces fournies pour une inscription (check-list des documents)")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pieces validees avec succes"),
        @ApiResponse(responseCode = "404", description = "Inscription non trouvee")
    })
    @PostMapping("/{id}/valider-pieces")
    public ResponseEntity<InscriptionDTO> validerPieces(@Parameter(description = "ID de l'inscription") @PathVariable Long id) {
        try {
            Inscription validated = service.validerPiecesInscription(id);
            return ResponseEntity.ok(mapper.toDto(validated));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Enregistrer le paiement d'inscription", description = "Enregistre le paiement des frais d'inscription (5000, 10000 ou 3000 F)")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Paiement enregistre"),
        @ApiResponse(responseCode = "404", description = "Inscription non trouvee")
    })
    @PostMapping("/{id}/enregistrer-paiement")
    public ResponseEntity<InscriptionDTO> enregistrerPaiement(@Parameter(description = "ID de l'inscription") @PathVariable Long id) {
        try {
            Inscription updated = service.enregistrerPaiementInscription(id);
            return ResponseEntity.ok(mapper.toDto(updated));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Inscriptions actives d'un eleve", description = "Retourne les inscriptions en cours (ACTIVE ou VALIDEE) pour un eleve")
    @ApiResponse(responseCode = "200", description = "Liste des inscriptions actives")
    @GetMapping("/eleve/{eleveId}/actives")
    public ResponseEntity<List<InscriptionDTO>> getInscriptionsActives(@Parameter(description = "ID de l'eleve") @PathVariable UUID eleveId) {
        List<InscriptionDTO> dtos = service.obtenirInscriptionsActives(eleveId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InscriptionDTO> update(@PathVariable Long id, @RequestBody InscriptionDTO dto) {
        Inscription inscription = mapper.toDomain(dto);
        Inscription updated = service.update(inscription);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @PostMapping("/{id}/terminer")
    public ResponseEntity<Void> terminer(@PathVariable Long id) {
        service.terminerInscription(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}


