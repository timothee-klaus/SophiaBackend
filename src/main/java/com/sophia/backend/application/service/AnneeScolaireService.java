package com.sophia.backend.application.service;
import com.sophia.backend.domain.model.AnneeScolaire;
import com.sophia.backend.domain.repository.AnneeScolaireRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class AnneeScolaireService {
    private final AnneeScolaireRepository anneeScolaireRepository;
    
    public AnneeScolaireService(AnneeScolaireRepository anneeScolaireRepository) {
        this.anneeScolaireRepository = anneeScolaireRepository;
    }
    
    public Optional<AnneeScolaire> findByUuid(UUID id) {
        return anneeScolaireRepository.findByUuid(id);
    }

    // Alias pour compatibilité contrôleurs (uid == uuid)
    public Optional<AnneeScolaire> findByUid(UUID uid) {
        return findByUuid(uid);
    }
    
    public List<AnneeScolaire> findAll() {
        return anneeScolaireRepository.findAll();
    }
    
    /**
     * Trouve l'année scolaire actuellement active
     * Déterminée par les dates de début/fin vs aujourd'hui
     */
    public Optional<AnneeScolaire> findActive() {
        return anneeScolaireRepository.findActive();
    }
    
    /**
     * Crée une année scolaire avec UID auto-généré
     * L'attribut estActive est calculé, pas stocké
     */
    public AnneeScolaire create(AnneeScolaire anneeScolaire) {
        // Générer un UID unique si non fourni
        if (anneeScolaire.getUuid() == null) {
            anneeScolaire.setUuid(UUID.randomUUID());
        }
        
        // Initialiser les timestamps
        LocalDateTime now = LocalDateTime.now();
        anneeScolaire.setCreatedAt(now);
        anneeScolaire.setUpdatedAt(now);
        
        return anneeScolaireRepository.save(anneeScolaire);
    }
    
    /**
     * Met à jour une année scolaire en cherchant par UID
     */
    public AnneeScolaire update(UUID uid, AnneeScolaire anneUpdated) {
        AnneeScolaire existing = findByUuid(uid)
                .orElseThrow(() -> new IllegalArgumentException("Année scolaire avec UID " + uid + " non trouvée"));
        
        existing.setLibelle(anneUpdated.getLibelle());
        existing.setDateDebut(anneUpdated.getDateDebut());
        existing.setDateFin(anneUpdated.getDateFin());
        existing.setUpdatedAt(LocalDateTime.now());
        
        return anneeScolaireRepository.save(existing);
    }
    
    public void delete(UUID id) {
        anneeScolaireRepository.deleteByUuid(id);
    }

    /**
     * Supprime une année scolaire en cherchant par UID
     */
    public void deleteByUid(UUID uid) {
        AnneeScolaire annee = findByUuid(uid)
                .orElseThrow(() -> new IllegalArgumentException("Année scolaire avec UID " + uid + " non trouvée"));
        anneeScolaireRepository.deleteByUuid(annee.getUuid());
    }
    
    /**
     * Ouvre une année scolaire (crée avec dates valides)
     * L'année sera "active" automatiquement quand sa dateDebut arrive
     */
    public AnneeScolaire ouvrirAnneeScolaire(AnneeScolaire anneeScolaire) {
        return this.create(anneeScolaire);
    }
    
    /**
     * Clôture une année scolaire
     * NOTE: À usage administratif si besoin, mais le système
     * détermine automatiquement via les dates
     */
    public void cloturerAnneeScolaire(UUID anneeScolaireUuid) {
        anneeScolaireRepository.findByUuid(anneeScolaireUuid).ifPresent(annee -> {
            // On pourrait ajouter un champ "clôturé" si besoin
            // Pour l'instant, les dates gèrent tout
            anneeScolaireRepository.save(annee);
        });
    }
    
    /**
     * Obtient l'année scolaire actuellement active
     * Déterminée par les dates (système, pas utilisateur)
     */
    public Optional<AnneeScolaire> obtenirAnneeScolaireActive() {
        return this.findActive();
    }
}
