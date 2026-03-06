package com.sophia.backend.application.service;
import com.sophia.backend.domain.model.AnneeScolaire;
import com.sophia.backend.domain.repository.AnneeScolaireRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class AnneeScolaireService {
    private final AnneeScolaireRepository anneeScolaireRepository;
    public AnneeScolaireService(AnneeScolaireRepository anneeScolaireRepository) {
        this.anneeScolaireRepository = anneeScolaireRepository;
    }
    public Optional<AnneeScolaire> findById(Long id) {
        return anneeScolaireRepository.findById(id);
    }
    public List<AnneeScolaire> findAll() {
        return anneeScolaireRepository.findAll();
    }
    public Optional<AnneeScolaire> findActive() {
        return anneeScolaireRepository.findActive();
    }
    public AnneeScolaire create(AnneeScolaire anneeScolaire) {
        return anneeScolaireRepository.save(anneeScolaire);
    }
    public AnneeScolaire update(AnneeScolaire anneeScolaire) {
        return anneeScolaireRepository.save(anneeScolaire);
    }
    public void delete(Long id) {
        anneeScolaireRepository.deleteById(id);
    }
    public AnneeScolaire ouvrirAnneeScolaire(AnneeScolaire anneeScolaire) {
        anneeScolaire.setEstActive(true);
        return this.create(anneeScolaire);
    }
    public void cloturerAnneeScolaire(Long anneeScolaireId) {
        anneeScolaireRepository.findById(anneeScolaireId).ifPresent(annee -> {
            annee.setEstActive(false);
            anneeScolaireRepository.save(annee);
        });
    }
    public Optional<AnneeScolaire> obtenirAnneeScolaireActive() {
        return this.findActive();
    }
}
