package com.sophia.backend.application.service;

import com.sophia.backend.domain.enums.TypePaiement;
import com.sophia.backend.domain.model.Paiement;
import com.sophia.backend.domain.repository.PaiementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class PaiementService {
    private final PaiementRepository paiementRepository;

    public PaiementService(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    public Optional<Paiement> findByUuid(UUID uuid) {
        return paiementRepository.findByUuid(uuid);
    }

    public List<Paiement> findAll() {
        return paiementRepository.findAll();
    }

    public List<Paiement> findByInscriptionUuid(UUID inscriptionUuid) {
        return paiementRepository.findByInscriptionUuid(inscriptionUuid);
    }

    public Paiement create(Paiement paiement) {
        if (paiement.getTypePaiement() == null) {
            throw new IllegalArgumentException("typePaiement est requis");
        }
        if (paiement.getUuid() == null) {
            paiement.setUuid(UUID.randomUUID());
        }

        switch (paiement.getTypePaiement()) {
            case INSCRIPTION -> {
                if (paiement.getReferenceUuid() == null) {
                    if (paiement.getInscriptionUuid() == null) {
                        throw new IllegalArgumentException("Pour un paiement d'inscription, inscriptionUuid est requis");
                    }
                    paiement.setReferenceUuid(paiement.getInscriptionUuid());
                }
            }
            case SCOLARITE -> {
                if (paiement.getReferenceUuid() == null) {
                    throw new IllegalArgumentException("Pour un paiement de scolarite, referenceUuid (ex: tranche de paiement) est requis");
                }
            }
            case DIVERS -> {
                if (paiement.getReferenceUuid() == null) {
                    throw new IllegalArgumentException("Pour un paiement divers, referenceUuid (frais divers) est requis");
                }
            }
            default -> throw new IllegalArgumentException("Type de paiement non supporte: " + paiement.getTypePaiement());
        }

        return paiementRepository.save(paiement);
    }

    public Paiement enregistrerPaiement(Paiement paiement) {
        return create(paiement);
    }

    public Paiement update(UUID uuid, Paiement paiement) {
        paiement.setUuid(uuid);
        return paiementRepository.save(paiement);
    }

    public void deleteByUuid(UUID uuid) {
        paiementRepository.deleteByUuid(uuid);
    }

    public BigDecimal calculerSoldeRestantByUuid(UUID inscriptionUuid, BigDecimal montantTotal) {
        List<Paiement> paiements = this.findByInscriptionUuid(inscriptionUuid);
        BigDecimal totalPaye = paiements.stream()
                .map(Paiement::getMontant)
                .filter(m -> m != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return montantTotal.subtract(totalPaye);
    }

    public boolean estAJourByUuid(UUID inscriptionUuid, BigDecimal montantTotal) {
        return calculerSoldeRestantByUuid(inscriptionUuid, montantTotal).compareTo(BigDecimal.ZERO) == 0;
    }

    public List<Paiement> obtenirPaiementsEnRetardByUuid(UUID inscriptionUuid) {
        return this.findByInscriptionUuid(inscriptionUuid);
    }
}
