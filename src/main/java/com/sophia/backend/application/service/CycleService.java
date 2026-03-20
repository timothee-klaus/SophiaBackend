package com.sophia.backend.application.service;
import com.sophia.backend.domain.model.Cycle;
import com.sophia.backend.domain.repository.CycleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CycleService {
    private final CycleRepository cycleRepository;
    
    public CycleService(CycleRepository cycleRepository) {
        this.cycleRepository = cycleRepository;
    }
    
    public Optional<Cycle> findById(Long id) {
        return cycleRepository.findById(id);
    }

    /**
     * Cherche un cycle par son UID (identifiant unique exposé à l'API)
     */
    public Optional<Cycle> findByUid(UUID uid) {
        return cycleRepository.findByUuid(uid);
    }
    
    public List<Cycle> findAll() {
        return cycleRepository.findAll();
    }
    
    /**
     * Crée un nouveau cycle avec un UID généré automatiquement
     */
    public Cycle create(Cycle cycle) {
        // Générer un UUID unique si non fourni
        if (cycle.getUuid() == null) {
            cycle.setUuid(UUID.randomUUID());
        }
        
        // Initialiser les timestamps
        LocalDateTime now = LocalDateTime.now();
        cycle.setCreatedAt(now);
        cycle.setUpdatedAt(now);
        
        return cycleRepository.save(cycle);
    }
    
    /**
     * Met à jour un cycle en cherchant par UID
     */
    public Cycle update(UUID uid, Cycle cycleUpdated) {
        Cycle existing = findByUid(uid)
                .orElseThrow(() -> new IllegalArgumentException("Cycle avec UID " + uid + " non trouvé"));
        
        // Mettre à jour les champs
        existing.setNom(cycleUpdated.getNom());
        existing.setDescription(cycleUpdated.getDescription());
        existing.setOrdre(cycleUpdated.getOrdre());
        existing.setUpdatedAt(LocalDateTime.now());
        
        return cycleRepository.save(existing);
    }
    
    /**
     * Supprime un cycle par son ID interne
     */
    public void delete(Long id) {
        cycleRepository.deleteById(id);
    }

    /**
     * Supprime un cycle en cherchant par UID
     */
    public void deleteByUid(UUID uid) {
        Cycle cycle = findByUid(uid)
                .orElseThrow(() -> new IllegalArgumentException("Cycle avec UID " + uid + " non trouvé"));
        cycleRepository.deleteById(cycle.getId());
    }
    
    public List<Cycle> obtenirCyclesDisponibles() {
        return this.findAll();
    }
}
