package com.sophia.backend.application.service;

import com.sophia.backend.domain.model.FraisDivers;
import com.sophia.backend.domain.repository.FraisDiversRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class FraisDiversService {
    private final FraisDiversRepository fraisDiversRepository;

    public FraisDiversService(FraisDiversRepository fraisDiversRepository) {
        this.fraisDiversRepository = fraisDiversRepository;
    }

    public Optional<FraisDivers> findByUuid(UUID uuid) {
        return fraisDiversRepository.findByUuid(uuid);
    }

    public List<FraisDivers> findAll() {
        return fraisDiversRepository.findAll();
    }

    public List<FraisDivers> findByNiveauUuid(UUID niveauUuid) {
        return fraisDiversRepository.findByNiveauUuid(niveauUuid);
    }

    public List<FraisDivers> findByAnneeScolaireUuid(UUID anneeScolaireUuid) {
        return fraisDiversRepository.findByAnneeScolaireUuid(anneeScolaireUuid);
    }

    public FraisDivers create(FraisDivers frais) {
        if (frais.getUuid() == null) {
            frais.setUuid(UUID.randomUUID());
        }
        LocalDateTime now = LocalDateTime.now();
        frais.setCreatedAt(now);
        frais.setUpdatedAt(now);
        return fraisDiversRepository.save(frais);
    }

    public FraisDivers update(UUID uuid, FraisDivers fraisUpdated) {
        FraisDivers existing = findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Frais divers avec UUID " + uuid + " non trouvé"));
        existing.setDescription(fraisUpdated.getDescription());
        existing.setMontant(fraisUpdated.getMontant());
        existing.setNiveauUuid(fraisUpdated.getNiveauUuid());
        existing.setAnneeScolaireUuid(fraisUpdated.getAnneeScolaireUuid());
        existing.setUpdatedAt(LocalDateTime.now());
        return fraisDiversRepository.save(existing);
    }

    public void deleteByUuid(UUID uuid) {
        fraisDiversRepository.deleteByUuid(uuid);
    }

    public FraisDivers definirFraisDivers(UUID niveauUuid, UUID anneeScolaireUuid, FraisDivers fraisDivers) {
        fraisDivers.setNiveauUuid(niveauUuid);
        fraisDivers.setAnneeScolaireUuid(anneeScolaireUuid);
        return this.create(fraisDivers);
    }
}
