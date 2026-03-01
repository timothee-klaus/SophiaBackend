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
}

