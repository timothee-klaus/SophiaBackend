package com.sophia.backend.interfaces.web.controller;

import com.sophia.backend.application.dto.UtilisateurDTO;
import com.sophia.backend.application.service.UtilisateurService;
import com.sophia.backend.domain.model.Utilisateur;
import com.sophia.backend.infrastructure.persistence.mapper.UtilisateurMapper;
import com.sophia.backend.infrastructure.security.ErrorResponse;
import com.sophia.backend.infrastructure.security.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentification", description = "Gestion de l'authentification JWT et inscription")
public class AuthController {

    private final JwtService jwtService;
    private final UtilisateurService utilisateurService;
    private final UtilisateurMapper utilisateurMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthController(JwtService jwtService,
                         UtilisateurService utilisateurService,
                         UtilisateurMapper utilisateurMapper,
                         PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.utilisateurService = utilisateurService;
        this.utilisateurMapper = utilisateurMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    @Operation(
        summary = "Inscrire un nouvel utilisateur",
        description = "**[PUBLIC - Pas d'authentification requise]**\n\n" +
            "Crée un nouveau compte utilisateur dans le système.\n\n" +
            "**Champs obligatoires:**\n" +
            "- `nom`: Nom complet de l'utilisateur\n" +
            "- `email`: Email unique (sera utilisé pour le login)\n" +
            "- `motDePasse`: Mot de passe (min 8 caractères recommandé)\n" +
            "- `role`: SECRETAIRE ou DIRECTEUR\n" +
            "- `telephone`: Numéro de téléphone (optionnel)\n\n" +
            "**Process:**\n" +
            "1. Le mot de passe est hashé avec BCrypt\n" +
            "2. L'utilisateur est créé avec estActif=true\n" +
            "3. Un token JWT est généré automatiquement\n\n" +
            "**Réponse:**\n" +
            "- Informations de l'utilisateur créé\n" +
            "- Token JWT pour connexion immédiate"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Utilisateur créé avec succès"),
        @ApiResponse(responseCode = "400", description = "Données invalides ou email déjà utilisé"),
        @ApiResponse(responseCode = "500", description = "Erreur serveur")
    })
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            // Validation des données
            if (request.getNom() == null || request.getEmail() == null ||
                request.getMotDePasse() == null || request.getRole() == null) {
                return ResponseEntity.badRequest().body(
                    ErrorResponse.badRequest(
                        "Données d'inscription incomplètes",
                        "Les champs nom, email, motDePasse et role sont obligatoires"
                    ).toMap()
                );
            }

            // Vérifier si l'email existe déjà
            if (utilisateurService.findByEmail(request.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().body(
                    ErrorResponse.badRequest(
                        "Email déjà utilisé",
                        "Un utilisateur avec cet email existe déjà. Veuillez utiliser un autre email."
                    ).toMap()
                );
            }

            // Créer l'utilisateur
            UtilisateurDTO dto = new UtilisateurDTO();
            dto.setId(UUID.randomUUID());
            dto.setNom(request.getNom());
            dto.setEmail(request.getEmail());
            dto.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
            dto.setRole(request.getRole());
            dto.setTelephone(request.getTelephone());
            dto.setEstActif(true);

            Utilisateur utilisateur = utilisateurMapper.toDomain(dto);
            Utilisateur created = utilisateurService.create(utilisateur);
            UtilisateurDTO createdDTO = utilisateurMapper.toDto(created);

            // Générer un token JWT automatiquement
            String token = jwtService.generateToken(created.getUuid(), created.getEmail(), created.getRole().name());

            // Masquer le mot de passe dans la réponse
            createdDTO.setMotDePasse(null);

            Map<String, Object> response = new HashMap<>();
            response.put("status", 201);
            response.put("message", "Utilisateur créé avec succès");
            response.put("utilisateur", createdDTO);
            response.put("token", token);
            response.put("expiresIn", 86400);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ErrorResponse.internalServerError(
                    "Erreur lors de la création de l'utilisateur",
                    e.getMessage()
                ).toMap()
            );
        }
    }

    @PostMapping("/login")
    @Operation(
        summary = "Authentifier un utilisateur",
        description = "Génère un token JWT pour un utilisateur\n\n" +
            "**Body:**\n" +
            "- `userId`: UUID de l'utilisateur\n" +
            "- `email`: Email de l'utilisateur\n" +
            "- `role`: Rôle (SECRETAIRE, DIRECTEUR, SUPER_ADMIN)\n\n" +
            "**Réponse:**\n" +
            "- `token`: Token JWT à utiliser en header Authorization: Bearer <token>\n" +
            "- `expiresIn`: Temps d'expiration en secondes (86400 = 24h)"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Authentification réussie"),
        @ApiResponse(responseCode = "400", description = "Données manquantes ou invalides"),
        @ApiResponse(responseCode = "500", description = "Erreur serveur")
    })
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            // Validation des données
            if (request.getUserId() == null || request.getEmail() == null || request.getRole() == null) {
                return ResponseEntity.badRequest().body(
                    ErrorResponse.badRequest(
                        "Données de login incomplètes",
                        "Les champs userId, email et role sont obligatoires"
                    ).toMap()
                );
            }

            // Générer le token JWT
            String token = jwtService.generateToken(request.getUserId(), request.getEmail(), request.getRole());

            Map<String, Object> response = new HashMap<>();
            response.put("status", 200);
            response.put("message", "Authentification réussie");
            response.put("token", token);
            response.put("expiresIn", 86400);
            response.put("userId", request.getUserId());
            response.put("email", request.getEmail());
            response.put("role", request.getRole());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ErrorResponse.internalServerError(
                    "Erreur lors de la génération du token",
                    e.getMessage()
                ).toMap()
            );
        }
    }

    @GetMapping("/validate")
    @Operation(
        summary = "Valider un token JWT",
        description = "Vérifie si un token JWT est valide\n\n" +
            "**Header:**\n" +
            "- `Authorization: Bearer <token>`\n\n" +
            "**Réponse:**\n" +
            "- `valid`: true si valide, false sinon\n" +
            "- `userId`: UUID de l'utilisateur\n" +
            "- `role`: Rôle de l'utilisateur\n" +
            "- `email`: Email de l'utilisateur"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Validation complétée"),
        @ApiResponse(responseCode = "401", description = "Token manquant ou invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur serveur")
    })
    public ResponseEntity<?> validate(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    ErrorResponse.unauthorized(
                        "Token manquant",
                        "Veuillez fournir un token dans le header 'Authorization: Bearer <token>'"
                    ).toMap()
                );
            }

            String token = authHeader.replace("Bearer ", "");

            if (!jwtService.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    ErrorResponse.unauthorized(
                        "Token invalide ou expiré",
                        "Le token JWT n'est pas valide ou a expiré. Veuillez vous reconnecter."
                    ).toMap()
                );
            }

            UUID userId = jwtService.extractUserId(token);
            String role = jwtService.extractRole(token);
            String email = jwtService.extractEmail(token);

            Map<String, Object> response = new HashMap<>();
            response.put("status", 200);
            response.put("valid", true);
            response.put("userId", userId);
            response.put("email", email);
            response.put("role", role);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ErrorResponse.internalServerError(
                    "Erreur lors de la validation du token",
                    e.getMessage()
                ).toMap()
            );
        }
    }

    @PostMapping("/logout")
    @Operation(
        summary = "Déconnecter un utilisateur",
        description = "Endpoint de déconnexion (client-side: supprimer le token stocké)\n\n" +
            "**Header:**\n" +
            "- `Authorization: Bearer <token>` (optionnel)"
    )
    @ApiResponse(responseCode = "200", description = "Déconnecté avec succès")
    public ResponseEntity<?> logout() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", 200);
        response.put("message", "Déconnecté avec succès. Veuillez supprimer le token client-side.");
        return ResponseEntity.ok(response);
    }

    /**
     * DTO pour la requête de login
     */
    public static class LoginRequest {
        private UUID userId;
        private String email;
        private String role;

        public LoginRequest() {}

        public LoginRequest(UUID userId, String email, String role) {
            this.userId = userId;
            this.email = email;
            this.role = role;
        }

        public UUID getUserId() {
            return userId;
        }

        public void setUserId(UUID userId) {
            this.userId = userId;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }

    /**
     * DTO pour la requête d'inscription
     */
    public static class RegisterRequest {
        private String nom;
        private String email;
        private String motDePasse;
        private String role;
        private String telephone;

        public RegisterRequest() {}

        public RegisterRequest(String nom, String email, String motDePasse, String role, String telephone) {
            this.nom = nom;
            this.email = email;
            this.motDePasse = motDePasse;
            this.role = role;
            this.telephone = telephone;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMotDePasse() {
            return motDePasse;
        }

        public void setMotDePasse(String motDePasse) {
            this.motDePasse = motDePasse;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }
    }
}





