package com.sophia.backend.application.service;

import com.sophia.backend.domain.model.Eleve;
import com.sophia.backend.domain.repository.EleveRepository;
import com.sophia.backend.domain.enums.StatutDossier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
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
     * Créer un nouveau dossier élève
     */
    public Eleve creerDossierEleve(Eleve eleve) {
        Eleve saved = this.create(eleve);

        // Enregistrer la création dans les logs
        try {
            logService.enregistrerCreation(
                UUID.randomUUID(), // À remplacer par l'utilisateur connecté
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
            existant.setPhotoPath(eleveUpdated.getPhotoPath());
            return eleveRepository.save(existant);
        }).orElseThrow(() -> new IllegalArgumentException("Élève non trouvé"));

        // Enregistrer la modification dans les logs
        try {
            logService.enregistrerModification(
                UUID.randomUUID(),
                "ELEVE",
                id.toString(),
                "",
                "",
                "Modification dossier eleve: " + result.getMatricule()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
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
                    UUID.randomUUID(),
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
}

