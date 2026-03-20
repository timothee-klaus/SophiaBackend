package com.sophia.backend.infrastructure.config;

import com.sophia.backend.domain.model.Cycle;
import com.sophia.backend.domain.repository.CycleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Configuration pour l'initialisation des données de base du système
 * Crée les cycles scolaires au démarrage de l'application
 */
@Configuration
@Slf4j
public class DataInitializationConfig {

    /**
     * Initialise les cycles scolaires au démarrage
     * Crée: Préscolaire, Primaire, Collège, Lycée
     */
    @Bean
    public CommandLineRunner initializeCycles(CycleRepository cycleRepository) {
        return args -> {
            // Vérifier si les cycles existent déjà
            if (cycleRepository.findAll().size() > 0) {
                log.info("Les cycles existent déjà en base de données, pas d'initialisation");
                return;
            }

            log.info("Initialisation des cycles scolaires...");

            // Créer la liste des cycles
            List<Cycle> cycles = Arrays.asList(
                    new Cycle(
                            null,
                            UUID.randomUUID(),
                            "Préscolaire",
                            "Classe maternelle et jardin d'enfants",
                            0,
                            LocalDateTime.now(),
                            LocalDateTime.now()
                    ),
                    new Cycle(
                            null,
                            UUID.randomUUID(),
                            "Primaire",
                            "Cycle primaire (CP à CM2)",
                            1,
                            LocalDateTime.now(),
                            LocalDateTime.now()
                    ),
                    new Cycle(
                            null,
                            UUID.randomUUID(),
                            "Collège",
                            "Cycle collégial (6ème à 3ème)",
                            2,
                            LocalDateTime.now(),
                            LocalDateTime.now()
                    ),
                    new Cycle(
                            null,
                            UUID.randomUUID(),
                            "Lycée",
                            "Cycle lycéen (2nde à Terminale)",
                            3,
                            LocalDateTime.now(),
                            LocalDateTime.now()
                    )
            );

            // Sauvegarder tous les cycles
            cycles.forEach(cycleRepository::save);

            log.info("✅ {} cycles créés avec succès:", cycles.size());
            cycles.forEach(c -> log.info("   - {} (UID: {})", c.getNom(), c.getUuid()));
        };
    }
}


