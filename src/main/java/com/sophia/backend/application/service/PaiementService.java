package com.sophia.backend.application.service;

import com.sophia.backend.domain.model.Paiement;
import com.sophia.backend.domain.repository.PaiementRepository;
import org.springframework.stereotype.Service;

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
}

