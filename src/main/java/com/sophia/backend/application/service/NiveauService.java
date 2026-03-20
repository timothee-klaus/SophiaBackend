package com.sophia.backend.application.service;

import com.sophia.backend.domain.model.Cycle;
import com.sophia.backend.domain.model.Niveau;
import com.sophia.backend.domain.repository.CycleRepository;
import com.sophia.backend.domain.repository.NiveauRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class NiveauService {
    private final NiveauRepository niveauRepository;
    private final CycleRepository cycleRepository;

    public NiveauService(NiveauRepository niveauRepository, CycleRepository cycleRepository) {
        this.niveauRepository = niveauRepository;
        this.cycleRepository = cycleRepository;
    }

    public Optional<Niveau> findByUuid(UUID uuid) {
        return niveauRepository.findByUuid(uuid);
    }

    public List<Niveau> findAll() {
        return niveauRepository.findAll();
    }

    public List<Niveau> findByCycleUuid(UUID cycleUuid) {
        return niveauRepository.findByCycleUuid(cycleUuid);
    }

    /**
     * Crée un niveau. Génère un UUID unique pour le niveau.
     */
    public Niveau create(Niveau niveau) {
        // Générer un UUID unique si non fourni
        if (niveau.getUuid() == null) {
            niveau.setUuid(UUID.randomUUID());
        }

        // Renseigner l'id du cycle si seul l'UUID est fourni
        if (niveau.getCycleId() == null && niveau.getCycleUuid() != null) {
            Cycle cycle = cycleRepository.findByUuid(niveau.getCycleUuid())
                    .orElseThrow(() -> new IllegalArgumentException("Cycle non trouvé pour uuid=" + niveau.getCycleUuid()));
            niveau.setCycleId(cycle.getId());
        }
        
        // Initialiser les timestamps
        LocalDateTime now = LocalDateTime.now();
        niveau.setCreatedAt(now);
        niveau.setUpdatedAt(now);
        
        return niveauRepository.save(niveau);
    }

    public Niveau update(UUID uuid, Niveau niveau) {
        niveau.setUuid(uuid);
        niveau.setUpdatedAt(LocalDateTime.now());
        return niveauRepository.save(niveau);
    }

    public void deleteByUuid(UUID uuid) {
        niveauRepository.deleteByUuid(uuid);
    }

    /**
     * Configurer les cycles/niveaux d'un établissement
     * Pour un établissement donné, définir les cycles qu'il propose
     * et, pour chaque cycle, lister les classes existantes
     */
}
