package com.sophia.backend.application.service;
import com.sophia.backend.domain.model.FraisDivers;
import com.sophia.backend.domain.repository.FraisDiversRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class FraisDiversService {
    private final FraisDiversRepository fraisDiversRepository;
    public FraisDiversService(FraisDiversRepository fraisDiversRepository) {
        this.fraisDiversRepository = fraisDiversRepository;
    }
    public Optional<FraisDivers> findById(Long id) {
        return fraisDiversRepository.findById(id);
    }
    public List<FraisDivers> findAll() {
        return fraisDiversRepository.findAll();
    }
    public List<FraisDivers> findByNiveauId(Long niveauId) {
        return fraisDiversRepository.findByNiveauId(niveauId);
    }
    public List<FraisDivers> findByAnneeScolaireId(Long anneeScolaireId) {
        return fraisDiversRepository.findByAnneeScolaireId(anneeScolaireId);
    }
    public FraisDivers create(FraisDivers frais) {
        return fraisDiversRepository.save(frais);
    }
    public FraisDivers update(FraisDivers frais) {
        return fraisDiversRepository.save(frais);
    }
    public void delete(Long id) {
        fraisDiversRepository.deleteById(id);
    }
    public FraisDivers definirFraisDivers(Long niveauId, Long anneeScolaireId, FraisDivers fraisDivers) {
        fraisDivers.setNiveauId(niveauId);
        fraisDivers.setAnneeScolaireId(anneeScolaireId);
        return this.create(fraisDivers);
    }
    public List<FraisDivers> obtenirFraisDiversPourNiveau(Long niveauId) {
        return this.findByNiveauId(niveauId);
    }
}
