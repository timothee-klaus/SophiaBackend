package com.sophia.backend.application.service;

import com.sophia.backend.domain.model.Niveau;
import com.sophia.backend.domain.repository.NiveauRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NiveauService {
    private final NiveauRepository niveauRepository;

    public NiveauService(NiveauRepository niveauRepository) {
        this.niveauRepository = niveauRepository;
    }

    public Optional<Niveau> findById(Long id) {
        return niveauRepository.findById(id);
    }

    public List<Niveau> findAll() {
        return niveauRepository.findAll();
    }

    public List<Niveau> findByCycleId(Long cycleId) {
        return niveauRepository.findByCycleId(cycleId);
    }

    public List<Niveau> findByEtablissementId(Long etablissementId) {
        return niveauRepository.findByEtablissementId(etablissementId);
    }

    public Niveau create(Niveau niveau) {
        return niveauRepository.save(niveau);
    }

    public Niveau update(Niveau niveau) {
        return niveauRepository.save(niveau);
    }

    public void delete(Long id) {
        niveauRepository.deleteById(id);
    }

    /**
     * Configurer les cycles/niveaux d'un établissement
     * Pour un établissement donné, définir les cycles qu'il propose
     * et, pour chaque cycle, lister les classes existantes
     */
    public List<Niveau> configurerNiveauxEtablissement(Long etablissementId, Long cycleId) {
        return this.findByEtablissementId(etablissementId).stream()
                .filter(n -> n.getCycleId().equals(cycleId))
                .toList();
    }
}

