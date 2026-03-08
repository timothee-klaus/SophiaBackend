package com.sophia.backend.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String SECURITY_SCHEME_NAME = "Bearer Authentication";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Sophia Backend API")
                .version("1.0.0")
                .description("API complète de gestion des frais scolaires, inscriptions et paiements pour le système Sophia.\n\n" +
                    "**Fonctionnalités principales:**\n" +
                    "- Gestion des établissements et structures scolaires\n" +
                    "- Gestion des élèves et dossiers\n" +
                    "- Gestion des inscriptions\n" +
                    "- Suivi des paiements et frais\n" +
                    "- Génération de reçus\n" +
                    "- Système de notifications\n" +
                    "- Audit trail complet\n\n" +
                    "**Acteurs:**\n" +
                    "- Secrétaire: Gestion complète des dossiers\n" +
                    "- Directeur: Consultation et rapports\n" +
                    "- Super Admin: Accès complet au système\n\n" +
                    "**Authentification:**\n" +
                    "1. Utilisez POST /api/v1/auth/login pour obtenir un token JWT\n" +
                    "2. Cliquez sur le bouton 'Authorize' (🔓) en haut à droite\n" +
                    "3. Entrez votre token dans le champ 'Value' (sans 'Bearer ')\n" +
                    "4. Cliquez sur 'Authorize' puis 'Close'\n" +
                    "5. Le token sera automatiquement ajouté à toutes vos requêtes\n\n" +
                    "**Rôles disponibles:**\n" +
                    "- SECRETAIRE: Accès complet (sauf gestion utilisateurs)\n" +
                    "- DIRECTEUR: Consultation et rapports uniquement\n" +
                    "- SUPER_ADMIN: Accès administrateur complet")
                .contact(new Contact()
                    .name("Equipe Sophia")
                    .email("support@sophia.local"))
                .license(new License()
                    .name("Apache 2.0")
                    .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
            .addServersItem(new Server()
                .url("http://localhost:8080")
                .description("Serveur de développement"))
            .addServersItem(new Server()
                .url("http://api.sophia.local")
                .description("Serveur de production"))
            // Configuration du schéma de sécurité JWT
            .components(new Components()
                .addSecuritySchemes(SECURITY_SCHEME_NAME, new SecurityScheme()
                    .name(SECURITY_SCHEME_NAME)
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
                    .description("Entrez le token JWT obtenu depuis l'endpoint POST /api/v1/auth/login\n\n" +
                        "Le token sera automatiquement ajouté au header Authorization: Bearer <token>")))
            // Appliquer la sécurité globalement
            .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME));
    }
}

