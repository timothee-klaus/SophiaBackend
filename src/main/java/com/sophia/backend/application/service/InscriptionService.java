package com.sophia.backend.application.service;

import com.sophia.backend.domain.model.Inscription;
import com.sophia.backend.domain.repository.InscriptionRepository;
import com.sophia.backend.domain.enums.StatutInscription;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class InscriptionService {
    private final InscriptionRepository inscriptionRepository;

    public InscriptionService(InscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }

    public Optional<Inscription> findByUuid(UUID uuid) {
        return inscriptionRepository.findByUuid(uuid);
    }

    public List<Inscription> findAll() {
        return inscriptionRepository.findAll();
    }

    public List<Inscription> findByEleveUuid(UUID eleveUuid) {
        return inscriptionRepository.findByEleveUuid(eleveUuid);
    }

    public List<Inscription> findByNiveauUuid(UUID niveauUuid) {
        return inscriptionRepository.findByNiveauUuid(niveauUuid);
    }

    public Inscription enregistrerDossierInscription(Inscription inscription) {
        if (inscription.getUuid() == null) {
            inscription.setUuid(UUID.randomUUID());
        }
        inscription.setCreatedAt(LocalDateTime.now());
        inscription.setUpdatedAt(LocalDateTime.now());
        if (inscription.getStatut() == null) {
            inscription.setStatut(StatutInscription.ACTIVE);
        }
        return inscriptionRepository.save(inscription);
    }

    public Inscription update(UUID uuid, Inscription inscription) {
        inscription.setUuid(uuid);
        inscription.setUpdatedAt(LocalDateTime.now());
        return inscriptionRepository.save(inscription);
    }

    public void deleteByUuid(UUID uuid) {
        inscriptionRepository.deleteByUuid(uuid);
    }

    public Inscription validerPiecesInscription(UUID inscriptionUuid) {
        Inscription insc = inscriptionRepository.findByUuid(inscriptionUuid)
                .orElseThrow(() -> new IllegalArgumentException("Inscription non trouvée"));
        insc.setStatut(StatutInscription.ACTIVE);
        insc.setUpdatedAt(LocalDateTime.now());
        return inscriptionRepository.save(insc);
    }

    public Inscription enregistrerPaiementInscription(UUID inscriptionUuid) {
        Inscription insc = inscriptionRepository.findByUuid(inscriptionUuid)
                .orElseThrow(() -> new IllegalArgumentException("Inscription non trouvée"));
        insc.setStatut(StatutInscription.ACTIVE);
        insc.setUpdatedAt(LocalDateTime.now());
        return inscriptionRepository.save(insc);
    }
}
