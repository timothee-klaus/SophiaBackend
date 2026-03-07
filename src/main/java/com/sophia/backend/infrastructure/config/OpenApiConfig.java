package com.sophia.backend.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

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
                    "- Système: Calculs et notifications automatiques")
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
                .description("Serveur de production"));
    }
}

