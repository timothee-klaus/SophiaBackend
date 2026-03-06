package com.sophia.backend.application.service;
import com.sophia.backend.domain.model.FraisInscription;
import com.sophia.backend.domain.repository.FraisInscriptionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class FraisInscriptionService {
    private final FraisInscriptionRepository fraisInscriptionRepository;
    public FraisInscriptionService(FraisInscriptionRepository fraisInscriptionRepository) {
        this.fraisInscriptionRepository = fraisInscriptionRepository;
    }
    public Optional<FraisInscription> findById(Long id) {
        return fraisInscriptionRepository.findById(id);
    }
    public List<FraisInscription> findAll() {
        return fraisInscriptionRepository.findAll();
    }
    public List<FraisInscription> findByCycleId(Long cycleId) {
        return fraisInscriptionRepository.findByCycleId(cycleId);
    }
    public List<FraisInscription> findByAnneeScolaireId(Long anneeScolaireId) {
        return fraisInscriptionRepository.findByAnneeScolaireId(anneeScolaireId);
    }
    public FraisInscription create(FraisInscription frais) {
        return fraisInscriptionRepository.save(frais);
    }
    public FraisInscription update(FraisInscription frais) {
        return fraisInscriptionRepository.save(frais);
    }
    public void delete(Long id) {
        fraisInscriptionRepository.deleteById(id);
    }
    public Optional<FraisInscription> obtenirFraisInscriptionPourCycle(Long cycleId, Long anneeScolaireId) {
        return this.findByCycleId(cycleId).stream()
                .filter(f -> f.getAnneeScolaireId().equals(anneeScolaireId))
                .findFirst();
    }
}
