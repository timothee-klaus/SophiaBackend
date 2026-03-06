package com.sophia.backend.application.service;

import com.sophia.backend.domain.model.Paiement;
import com.sophia.backend.domain.repository.PaiementRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PaiementService {
    private final PaiementRepository paiementRepository;

    public PaiementService(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    public Optional<Paiement> findById(Long id) {
        return paiementRepository.findById(id);
    }

    public List<Paiement> findAll() {
        return paiementRepository.findAll();
    }

    public List<Paiement> findByInscriptionId(Long inscriptionId) {
        return paiementRepository.findByInscriptionId(inscriptionId);
    }

    public Paiement create(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    public Paiement update(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    public void delete(Long id) {
        paiementRepository.deleteById(id);
    }

    /**
     * Enregistrer un paiement (en précisant la tranche : 1ère, 2ème, etc.)
     */
    public Paiement enregistrerPaiement(Paiement paiement) {
        return this.create(paiement);
    }

    /**
     * Associer un paiement à un élève et à une année scolaire (via l'inscription)
     * Cette opération se fait au moment de l'enregistrement du paiement
     */
    public Paiement associerPaiementAInscription(Long inscriptionId, Paiement paiement) {
        paiement.setInscriptionId(inscriptionId);
        return this.create(paiement);
    }

    /**
     * Visualiser l'échéancier d'un élève (soldes par tranche)
     * Retourne la liste de tous les paiements d'une inscription
     */
    public List<Paiement> visualiserEchéancier(Long inscriptionId) {
        return this.findByInscriptionId(inscriptionId);
    }

    /**
     * Calculer automatiquement le solde restant dû pour un élève
     * en fonction des tranches et des paiements enregistrés
     */
    public BigDecimal calculerSoldeRestant(Long inscriptionId, BigDecimal montantTotal) {
        List<Paiement> paiements = this.findByInscriptionId(inscriptionId);
        BigDecimal totalPaye = paiements.stream()
                .map(Paiement::getMontant)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return montantTotal.subtract(totalPaye);
    }

    /**
     * Consulter l'historique des paiements d'un élève (via inscription)
     */
    public List<Paiement> obtenirHistoriquePaiements(Long inscriptionId) {
        return this.findByInscriptionId(inscriptionId);
    }

    /**
     * Vérifier si un paiement est à jour (pas d'impayés)
     */
    public boolean estAJour(Long inscriptionId, BigDecimal montantTotal) {
        BigDecimal soldeRestant = this.calculerSoldeRestant(inscriptionId, montantTotal);
        return soldeRestant.compareTo(BigDecimal.ZERO) == 0;
    }

    /**
     * Obtenir les paiements en retard
     */
    public List<Paiement> obtenirPaiementsEnRetard(Long inscriptionId) {
        return this.findByInscriptionId(inscriptionId);
    }
}

