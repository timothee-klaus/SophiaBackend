package com.sophia.backend.application.service;

import com.sophia.backend.domain.model.FraisInscription;
import com.sophia.backend.domain.repository.FraisInscriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class FraisInscriptionService {
    private final FraisInscriptionRepository repository;

    public FraisInscriptionService(FraisInscriptionRepository repository) {
        this.repository = repository;
    }

    public Optional<FraisInscription> findByUuid(UUID uuid) {
        return repository.findByUuid(uuid);
    }

    public List<FraisInscription> findAll() {
        return repository.findAll();
    }

    public List<FraisInscription> findByCycleUuid(UUID cycleUuid) {
        return repository.findByCycleUuid(cycleUuid);
    }

    public List<FraisInscription> findByAnneeScolaireUuid(UUID anneeScolaireUuid) {
        return repository.findByAnneeScolaireUuid(anneeScolaireUuid);
    }

    public FraisInscription create(FraisInscription frais) {
        if (frais.getUuid() == null) frais.setUuid(UUID.randomUUID());
        LocalDateTime now = LocalDateTime.now();
        if (frais.getCreatedAt() == null) frais.setCreatedAt(now);
        frais.setUpdatedAt(now);
        return repository.save(frais);
    }

    public FraisInscription update(UUID uuid, FraisInscription frais) {
        frais.setUuid(uuid);
        repository.findByUuid(uuid)
                .map(FraisInscription::getCreatedAt)
                .ifPresent(frais::setCreatedAt);
        frais.setUpdatedAt(LocalDateTime.now());
        return repository.save(frais);
    }

    public void deleteByUuid(UUID uuid) {
        repository.deleteByUuid(uuid);
    }
}
