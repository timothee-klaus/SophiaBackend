package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.EtablissementDTO;
import com.sophia.backend.application.service.EtablissementService;
import com.sophia.backend.domain.model.Etablissement;
import com.sophia.backend.infrastructure.persistence.mapper.EtablissementMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Établissements", description = "Gestion des établissements scolaires")
@RestController
@RequestMapping("/api/v1/etablissements")
@RequiredArgsConstructor
public class EtablissementController {

    private final EtablissementService service;
    private final EtablissementMapper mapper;

    @Operation(
        summary = "Lister tous les etablissements",
        description = "Retourne la liste de tous les etablissements du systeme.\n\n" +
            "**Utilité**: Afficher tous les etablissements existants pour consultation ou selection.\n\n" +
            "**Parametres**: Aucun\n\n" +
            "**Reponse**: Array de EtablissementDTO avec (id, nom, adresse, telephone, email, logo, statut, dates)"
    )
    @ApiResponse(responseCode = "200", description = "Liste des etablissements recuperee avec succes")
    @GetMapping
    public ResponseEntity<List<EtablissementDTO>> getAll() {
        List<EtablissementDTO> dtos = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(
        summary = "Recuperer un etablissement par ID",
        description = "Retourne les details complets d'un etablissement specifique.\n\n" +
            "**Utilité**: Consulter les informations detaillees d'un etablissement (nom, adresse, contact, logo).\n\n" +
            "**Parametres**:\n" +
            "- `id` (Long, path): Identifiant unique de l'etablissement\n\n" +
            "**Reponse**: EtablissementDTO avec tous les details"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Etablissement trouve"),
        @ApiResponse(responseCode = "404", description = "Etablissement non trouve")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EtablissementDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(e -> ResponseEntity.ok(mapper.toDto(e)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
        summary = "Filtrer les etablissements par statut",
        description = "Retourne les etablissements correspondant au statut specifie.\n\n" +
            "**Utilité**: Afficher uniquement les etablissements actifs ou inactifs.\n\n" +
            "**Parametres**:\n" +
            "- `statut` (String, path): ACTIF ou INACTIF\n\n" +
            "**Reponse**: List[EtablissementDTO] filtres par statut"
    )
    @ApiResponse(responseCode = "200", description = "Etablissements filtres recuperes")
    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<EtablissementDTO>> getByStatut(@PathVariable String statut) {
        List<EtablissementDTO> dtos = service.findByStatut(statut).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(
        summary = "Creer un nouvel etablissement",
        description = "Cree un nouvel etablissement scolaire dans le systeme.\n\n" +
            "**Utilité**: Ajouter une nouvelle ecole au systeme avec ses informations de base.\n\n" +
            "**Corps de requete**: EtablissementDTO avec:\n" +
            "- `nom` (String, requis): Nom de l'etablissement (ex: 'Institut Sophia')\n" +
            "- `adresse` (String, requis): Adresse complete\n" +
            "- `telephone` (String, requis): Numero de telephone\n" +
            "- `email` (String, requis): Email de contact\n" +
            "- `logo` (String, optionnel): Chemin/URL du logo\n" +
            "- `statut` (String): ACTIF ou INACTIF\n\n" +
            "**Reponse**: EtablissementDTO cree avec ID genere"
    )
    @ApiResponse(responseCode = "201", description = "Etablissement cree avec succes")
    @PostMapping
    public ResponseEntity<EtablissementDTO> create(@RequestBody EtablissementDTO dto) {
        Etablissement etablissement = mapper.toDomain(dto);
        Etablissement saved = service.creerEtablissement(etablissement);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @Operation(
        summary = "Modifier un etablissement",
        description = "Met a jour les informations d'un etablissement existant.\n\n" +
            "**Utilité**: Changer les details d'une ecole (nom, adresse, contact, statut).\n\n" +
            "**Parametres**:\n" +
            "- `id` (Long, path): ID de l'etablissement a modifier\n\n" +
            "**Corps**: Nouveaux details de l'etablissement\n\n" +
            "**Reponse**: EtablissementDTO modifie"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Etablissement modifie avec succes"),
        @ApiResponse(responseCode = "404", description = "Etablissement non trouve")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EtablissementDTO> update(@PathVariable Long id, @RequestBody EtablissementDTO dto) {
        Etablissement etablissement = mapper.toDomain(dto);
        try {
            Etablissement updated = service.modifierEtablissement(id, etablissement);
            return ResponseEntity.ok(mapper.toDto(updated));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
        summary = "Supprimer un etablissement",
        description = "Supprime un etablissement du systeme (suppression logique ou physique).\n\n" +
            "**Utilité**: Retirer une ecole du systeme.\n\n" +
            "**Parametres**:\n" +
            "- `id` (Long, path): ID de l'etablissement a supprimer\n\n" +
            "**Reponse**: Vide (204 No Content)"
    )
    @ApiResponse(responseCode = "204", description = "Etablissement supprime avec succes")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.supprimerEtablissement(id);
        return ResponseEntity.noContent().build();
    }
}


