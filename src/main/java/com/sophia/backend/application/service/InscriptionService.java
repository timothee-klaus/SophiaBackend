package com.sophia.backend.application.service;

import com.sophia.backend.domain.model.Inscription;
import com.sophia.backend.domain.repository.InscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InscriptionService {
    private final InscriptionRepository inscriptionRepository;

    public InscriptionService(InscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }

    public Optional<Inscription> findById(Long id) {
        return inscriptionRepository.findById(id);
    }

    public List<Inscription> findAll() {
        return inscriptionRepository.findAll();
    }

    public List<Inscription> findByEleveId(UUID eleveId) {
        return inscriptionRepository.findByEleveId(eleveId);
    }

    public Inscription create(Inscription inscription) {
        return inscriptionRepository.save(inscription);
    }

    public Inscription update(Inscription inscription) {
        return inscriptionRepository.save(inscription);
    }

    public void delete(Long id) {
        inscriptionRepository.deleteById(id);
    }

    /**
     * Enregistrer le dépôt d'un dossier d'inscription
     */
    public Inscription enregistrerDossierInscription(Inscription inscription) {
        inscription.setStatut("ACTIVE");
        return this.create(inscription);
    }

    /**
     * Valider les pièces fournies (check-list basée sur les fiches)
     */
    public Inscription validerPiecesInscription(Long inscriptionId) {
        return inscriptionRepository.findById(inscriptionId).map(inscription -> {
            inscription.setStatut("VALIDEE");
            return inscriptionRepository.save(inscription);
        }).orElseThrow(() -> new IllegalArgumentException("Inscription non trouvée"));
    }

    /**
     * Enregistrer le paiement des frais d'inscription
     */
    public Inscription enregistrerPaiementInscription(Long inscriptionId) {
        return inscriptionRepository.findById(inscriptionId).map(inscription -> {
            inscription.setStatut("PAYEE");
            return inscriptionRepository.save(inscription);
        }).orElseThrow(() -> new IllegalArgumentException("Inscription non trouvée"));
    }

    /**
     * Obtenir les inscriptions actives pour un élève
     */
    public List<Inscription> obtenirInscriptionsActives(UUID eleveId) {
        return this.findByEleveId(eleveId).stream()
                .filter(i -> "ACTIVE".equals(i.getStatut()) || "VALIDEE".equals(i.getStatut()))
                .toList();
    }

    /**
     * Marquer une inscription comme terminée
     */
    public void terminerInscription(Long inscriptionId) {
        inscriptionRepository.findById(inscriptionId).ifPresent(inscription -> {
            inscription.setStatut("TERMINEE");
            inscriptionRepository.save(inscription);
        });
    }
}

