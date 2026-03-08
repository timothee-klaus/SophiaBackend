package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.UtilisateurDTO;
import com.sophia.backend.application.service.UtilisateurService;
import com.sophia.backend.domain.model.Utilisateur;
import com.sophia.backend.infrastructure.persistence.mapper.UtilisateurMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/utilisateurs")
@Tag(name = "Utilisateurs", description = "Gestion des utilisateurs du système (réservé SUPER_ADMIN)")
public class UtilisateurController {

    private final UtilisateurService service;
    private final UtilisateurMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurController(UtilisateurService service, UtilisateurMapper mapper, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    @Operation(
        summary = "Créer un utilisateur",
        description = "**[SUPER_ADMIN ONLY]**\n\n" +
            "Crée un nouvel utilisateur dans le système.\n\n" +
            "**Champs obligatoires:**\n" +
            "- `nom`: Nom complet de l'utilisateur\n" +
            "- `email`: Email unique (utilisé pour le login)\n" +
            "- `motDePasse`: Mot de passe (sera hashé automatiquement)\n" +
            "- `role`: SECRETAIRE ou DIRECTEUR\n\n" +
            "**Note:** Le mot de passe est automatiquement hashé avec BCrypt avant stockage."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Utilisateur créé avec succès"),
        @ApiResponse(responseCode = "400", description = "Données invalides"),
        @ApiResponse(responseCode = "403", description = "Accès refusé - Réservé SUPER_ADMIN")
    })
    public ResponseEntity<UtilisateurDTO> create(@RequestBody UtilisateurDTO dto) {
        // Hasher le mot de passe
        dto.setMotDePasse(passwordEncoder.encode(dto.getMotDePasse()));

        Utilisateur utilisateur = mapper.toDomain(dto);
        Utilisateur saved = service.create(utilisateur);
        return ResponseEntity.ok(mapper.toDto(saved));
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Récupérer un utilisateur par ID",
        description = "**[SUPER_ADMIN ONLY]**\n\n" +
            "Récupère les détails d'un utilisateur spécifique.\n\n" +
            "**Note:** Le mot de passe n'est jamais retourné dans la réponse."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Utilisateur trouvé"),
        @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé"),
        @ApiResponse(responseCode = "403", description = "Accès refusé")
    })
    public ResponseEntity<UtilisateurDTO> getById(@PathVariable UUID id) {
        return service.findById(id)
            .map(mapper::toDto)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(
        summary = "Lister tous les utilisateurs",
        description = "**[SUPER_ADMIN ONLY]**\n\n" +
            "Récupère la liste de tous les utilisateurs du système.\n\n" +
            "**Inclut:** Secrétaires, Directeurs et Super Admins"
    )
    @ApiResponse(responseCode = "200", description = "Liste des utilisateurs")
    public ResponseEntity<List<UtilisateurDTO>> getAll() {
        List<UtilisateurDTO> utilisateurs = service.findAll().stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
        return ResponseEntity.ok(utilisateurs);
    }

    @GetMapping("/role/{role}")
    @Operation(
        summary = "Utilisateurs par rôle",
        description = "**[SUPER_ADMIN ONLY]**\n\n" +
            "Liste les utilisateurs d'un rôle spécifique.\n\n" +
            "**Rôles disponibles:**\n" +
            "- SECRETAIRE\n" +
            "- DIRECTEUR\n" +
            "- SUPER_ADMIN"
    )
    @ApiResponse(responseCode = "200", description = "Liste des utilisateurs du rôle")
    public ResponseEntity<List<UtilisateurDTO>> getByRole(@PathVariable String role) {
        List<UtilisateurDTO> utilisateurs = service.findByRole(role).stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
        return ResponseEntity.ok(utilisateurs);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Modifier un utilisateur",
        description = "**[SUPER_ADMIN ONLY]**\n\n" +
            "Modifie les informations d'un utilisateur existant.\n\n" +
            "**Note:** Si vous modifiez le mot de passe, il sera automatiquement hashé."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Utilisateur modifié"),
        @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé")
    })
    public ResponseEntity<UtilisateurDTO> update(@PathVariable UUID id, @RequestBody UtilisateurDTO dto) {
        dto.setId(id);

        // Si le mot de passe est fourni, le hasher
        if (dto.getMotDePasse() != null && !dto.getMotDePasse().isEmpty()) {
            dto.setMotDePasse(passwordEncoder.encode(dto.getMotDePasse()));
        }

        Utilisateur utilisateur = mapper.toDomain(dto);
        Utilisateur updated = service.update(utilisateur);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Supprimer un utilisateur",
        description = "**[SUPER_ADMIN ONLY]**\n\n" +
            "Supprime définitivement un utilisateur du système.\n\n" +
            "**Attention:** Cette action est irréversible. Utilisez plutôt 'désactiver' pour une suppression logique."
    )
    @ApiResponse(responseCode = "204", description = "Utilisateur supprimé")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activer")
    @Operation(
        summary = "Activer un utilisateur",
        description = "**[SUPER_ADMIN ONLY]**\n\n" +
            "Réactive un compte utilisateur précédemment désactivé.\n\n" +
            "L'utilisateur pourra à nouveau se connecter au système."
    )
    @ApiResponse(responseCode = "200", description = "Utilisateur activé")
    public ResponseEntity<UtilisateurDTO> activer(@PathVariable UUID id) {
        return service.findById(id)
            .map(utilisateur -> {
                utilisateur.setEstActif(true);
                Utilisateur updated = service.update(utilisateur);
                return mapper.toDto(updated);
            })
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/desactiver")
    @Operation(
        summary = "Désactiver un utilisateur",
        description = "**[SUPER_ADMIN ONLY]**\n\n" +
            "Désactive un compte utilisateur sans le supprimer.\n\n" +
            "L'utilisateur ne pourra plus se connecter mais ses données sont conservées."
    )
    @ApiResponse(responseCode = "200", description = "Utilisateur désactivé")
    public ResponseEntity<UtilisateurDTO> desactiver(@PathVariable UUID id) {
        return service.findById(id)
            .map(utilisateur -> {
                utilisateur.setEstActif(false);
                Utilisateur updated = service.update(utilisateur);
                return mapper.toDto(updated);
            })
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}


