package com.sophia.backend.application.service;

import com.sophia.backend.domain.model.Eleve;
import com.sophia.backend.domain.repository.EleveRepository;
import com.sophia.backend.domain.enums.StatutDossier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class EleveService {
    private final EleveRepository eleveRepository;
    private final LogService logService;

    public EleveService(EleveRepository eleveRepository, LogService logService) {
        this.eleveRepository = eleveRepository;
        this.logService = logService;
    }

    public Optional<Eleve> findById(UUID id) {
        return eleveRepository.findById(id);
    }

    public Optional<Eleve> findByUuid(UUID uuid) {
        return eleveRepository.findByUuid(uuid);
    }

    public Optional<Eleve> findByMatricule(String matricule) {
        return eleveRepository.findByMatricule(matricule);
    }

    public List<Eleve> findAll() {
        return eleveRepository.findAll();
    }

    public Eleve create(Eleve eleve) {
        return eleveRepository.save(eleve);
    }

    public Eleve update(Eleve eleve) {
        return eleveRepository.save(eleve);
    }

    public void delete(UUID id) {
        eleveRepository.deleteById(id);
    }

    /**
     * Génère un matricule unique pour un élève
     * Format: ELEV + timestamp + nombre aléatoire
     * Exemple: ELEV20260316120530789
     */
    private String genererMatricule() {
        long timestamp = System.currentTimeMillis();
        int random = (int) (Math.random() * 1000);
        return String.format("ELEV%d%d", timestamp, random);
    }

    /**
     * Créer un nouveau dossier élève
     * Initialise automatiquement:
     * - Le matricule (généré par le système)
     * - Le statut du dossier (INCOMPLET par défaut)
     * - La date de création du dossier (aujourd'hui)
     * - Les timestamps createdAt et updatedAt
     */
    public Eleve creerDossierEleve(Eleve eleve) {
        // 1. Générer le matricule si non fourni
        if (eleve.getMatricule() == null || eleve.getMatricule().trim().isEmpty()) {
            eleve.setMatricule(genererMatricule());
        }

        // 2. Déterminer automatiquement le statut du dossier en fonction des pièces fournies
        // 2. Initialiser le statut du dossier (INCOMPLET par défaut)
        if (eleve.getStatutDossier() == null) {
            eleve.setStatutDossier(StatutDossier.INCOMPLET);
        }
        if (eleve.getDateCreationDossier() == null) {
            eleve.setDateCreationDossier(LocalDate.now());
        }

        // 4. Initialiser les timestamps
        LocalDateTime now = LocalDateTime.now();
        eleve.setCreatedAt(now);
        eleve.setUpdatedAt(now);

        // 5. Enregistrer en base de données
        Eleve saved = this.create(eleve);

        // 6. Enregistrer la création dans les logs
        try {
            logService.enregistrerCreation(
                "ELEVE",
                saved.getId().toString(),
                "Creation dossier eleve: " + saved.getMatricule()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return saved;
    }

    /**
     * Consulter/modifier les informations d'un élève
     */
    public Eleve modifierEleve(UUID id, Eleve eleveUpdated) {
        Eleve result = eleveRepository.findById(id).map(existant -> {
            existant.setNom(eleveUpdated.getNom());
            existant.setPrenom(eleveUpdated.getPrenom());
            existant.setDateNaissance(eleveUpdated.getDateNaissance());
            existant.setLieuNaissance(eleveUpdated.getLieuNaissance());
            existant.setSexe(eleveUpdated.getSexe());
            existant.setNationalite(eleveUpdated.getNationalite());
            existant.setAdresse(eleveUpdated.getAdresse());
            existant.setNomTuteur(eleveUpdated.getNomTuteur());
            existant.setTelephoneTuteur(eleveUpdated.getTelephoneTuteur());
            existant.setEmailTuteur(eleveUpdated.getEmailTuteur());
            existant.setPhotoFournie(eleveUpdated.isPhotoFournie());
            existant.setActeNaissanceFourni(eleveUpdated.isActeNaissanceFourni());
            existant.setCertificatResidenceFourni(eleveUpdated.isCertificatResidenceFourni());
            existant.setBulletinsFournis(eleveUpdated.isBulletinsFournis());
            existant.setUpdatedAt(LocalDateTime.now());
            return existant;
        }).orElseThrow(() -> new IllegalArgumentException("Élève non trouvé"));

        Eleve saved = eleveRepository.save(result);

        // Enregistrer la modification dans les logs
        try {
            logService.enregistrerModification(
                "ELEVE",
                id.toString(),
                "",
                "",
                "Modification dossier eleve: " + saved.getMatricule()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return saved;
    }

    /**
     * Archiver un élève (départ, fin de cycle)
     */
    public void archiverEleve(UUID id) {
        eleveRepository.findById(id).ifPresent(eleve -> {
            eleve.setStatutDossier(StatutDossier.INCOMPLET);
            eleveRepository.save(eleve);

            // Enregistrer l'archivage dans les logs
            try {
                logService.enregistrerModification(
                    "ELEVE",
                    id.toString(),
                    "",
                    "",
                    "Archivage eleve: " + eleve.getMatricule()
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Rechercher un élève et consulter sa fiche financière
     */
    public Optional<Eleve> rechercherEleve(UUID id) {
        return this.findById(id);
    }

    /**
     * Règle de calcul du statut dossier selon les pièces fournies.
     * - COMPLET si toutes les pièces sont présentes
     * - EN_COURS si au moins une pièce est fournie mais il manque encore des documents
     * - INCOMPLET si aucune pièce n'est fournie
     */
    private StatutDossier recalculerStatutDossier(Eleve eleve) {
        boolean photo = eleve.isPhotoFournie();
        boolean acte = eleve.isActeNaissanceFourni();
        boolean certificat = eleve.isCertificatResidenceFourni();
        boolean bulletins = eleve.isBulletinsFournis();

        if (photo && acte && certificat && bulletins) {
            return StatutDossier.COMPLET;
        }
        if (photo || acte || certificat || bulletins) {
            return StatutDossier.EN_COURS;
        }
        return StatutDossier.INCOMPLET;
    }
}
