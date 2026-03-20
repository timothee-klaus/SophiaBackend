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
                .description("API de gestion des frais scolaires pour une école unique.\n\n" +
                    "**Fonctionnalités principales:**\n" +
                    "- Gestion de la structure scolaire (cycles, niveaux)\n" +
                    "- Gestion des élèves et inscriptions\n" +
                    "- Suivi des paiements et frais\n" +
                    "- Génération de reçus\n" +
                    "- Système de notifications\n" +
                    "- Gestion des blocages\n\n" +
                    "**Applications clientes:**\n" +
                    "- Web (Secrétariat): Gestion complète\n" +
                    "- Mobile (Direction): Consultation et rapports")
                .contact(new Contact()
                    .name("Equipe Sophia")
                    .email("support@sophia.local"))
                .license(new License()
                    .name("Apache 2.0")
                    .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
            .addServersItem(new Server()
                .url("http://localhost:8080")
                .description("Serveur de développement"));
    }
}
