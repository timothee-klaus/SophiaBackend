package com.sophia.backend.application.service;
import com.sophia.backend.domain.model.TranchePaiement;
import com.sophia.backend.domain.repository.TranchePaiementRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class TranchePaiementService {
    private final TranchePaiementRepository tranchePaiementRepository;
    public TranchePaiementService(TranchePaiementRepository tranchePaiementRepository) {
        this.tranchePaiementRepository = tranchePaiementRepository;
    }
    public Optional<TranchePaiement> findById(Long id) {
        return tranchePaiementRepository.findById(id);
    }
    public List<TranchePaiement> findAll() {
        return tranchePaiementRepository.findAll();
    }
    public List<TranchePaiement> findByFraisScolaireId(Long fraisScolaireId) {
        return tranchePaiementRepository.findByFraisScolaireId(fraisScolaireId);
    }
    public TranchePaiement create(TranchePaiement tranche) {
        return tranchePaiementRepository.save(tranche);
    }
    public TranchePaiement update(TranchePaiement tranche) {
        return tranchePaiementRepository.save(tranche);
    }
    public void delete(Long id) {
        tranchePaiementRepository.deleteById(id);
    }
    public List<TranchePaiement> visualiserEchéancierParFraisScolaire(Long fraisScolaireId) {
        return this.findByFraisScolaireId(fraisScolaireId);
    }
}
