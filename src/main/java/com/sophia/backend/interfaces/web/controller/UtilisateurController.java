package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.UtilisateurDTO;
import com.sophia.backend.application.service.UtilisateurService;
import com.sophia.backend.domain.model.Utilisateur;
import com.sophia.backend.infrastructure.persistence.mapper.UtilisateurMapper;
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

@Tag(name = "Utilisateurs", description = "Gestion des utilisateurs - Secretaires et Directeurs")
@RestController
@RequestMapping("/api/v1/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurService service;
    private final UtilisateurMapper mapper;

    @Operation(summary = "Lister tous les utilisateurs", description = "Retourne tous les utilisateurs du systeme")
    @GetMapping
    public ResponseEntity<List<UtilisateurDTO>> getAll() {
        List<UtilisateurDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Recuperer un utilisateur par ID", description = "Retourne les details d'un utilisateur")
    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDTO> getById(@Parameter(description = "ID de l'utilisateur (UUID)") @PathVariable UUID id) {
        return service.findById(id)
                .map(u -> ResponseEntity.ok(mapper.toDto(u)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Rechercher par email", description = "Recherche un utilisateur par son email")
    @GetMapping("/email/{email}")
    public ResponseEntity<UtilisateurDTO> getByEmail(@Parameter(description = "Email de l'utilisateur") @PathVariable String email) {
        return service.findByEmail(email)
                .map(u -> ResponseEntity.ok(mapper.toDto(u)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Filtrer par role", description = "Liste les utilisateurs par role (SECRETAIRE ou DIRECTEUR)")
    @GetMapping("/role/{role}")
    public ResponseEntity<List<UtilisateurDTO>> getByRole(@Parameter(description = "Role: SECRETAIRE ou DIRECTEUR") @PathVariable String role) {
        List<UtilisateurDTO> dtos = service.findByRole(role).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<UtilisateurDTO> create(@RequestBody UtilisateurDTO dto) {
        Utilisateur utilisateur = mapper.toDomain(dto);
        Utilisateur saved = service.create(utilisateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @PostMapping("/login")
    public ResponseEntity<UtilisateurDTO> login(
            @RequestParam String email,
            @RequestParam String motDePasse) {
        return service.authentifier(email, motDePasse)
                .map(u -> ResponseEntity.ok(mapper.toDto(u)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @GetMapping("/secretaires")
    public ResponseEntity<List<UtilisateurDTO>> obtenirSecretaires() {
        List<UtilisateurDTO> dtos = service.obtenirSecretaires().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/directeurs")
    public ResponseEntity<List<UtilisateurDTO>> obtenirDirecteurs() {
        List<UtilisateurDTO> dtos = service.obtenirDirecteurs().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurDTO> update(@PathVariable UUID id, @RequestBody UtilisateurDTO dto) {
        Utilisateur utilisateur = mapper.toDomain(dto);
        Utilisateur updated = service.update(utilisateur);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}


