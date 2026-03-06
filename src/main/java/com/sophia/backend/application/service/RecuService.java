package com.sophia.backend.application.service;
import com.sophia.backend.domain.model.Recu;
import com.sophia.backend.domain.repository.RecuRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class RecuService {
    private final RecuRepository recuRepository;
    public RecuService(RecuRepository recuRepository) {
        this.recuRepository = recuRepository;
    }
    public Optional<Recu> findById(Long id) {
        return recuRepository.findById(id);
    }
    public List<Recu> findAll() {
        return recuRepository.findAll();
    }
    public List<Recu> findByPaiementId(Long paiementId) {
        return recuRepository.findByPaiementId(paiementId);
    }
    public Recu create(Recu recu) {
        return recuRepository.save(recu);
    }
    public Recu update(Recu recu) {
        return recuRepository.save(recu);
    }
    public void delete(Long id) {
        recuRepository.deleteById(id);
    }
    public Recu genererRecuInscription(Long paiementId, UUID secretaireId) {
        Recu recu = new Recu();
        recu.setPaiementId(paiementId);
        recu.setDemandePar(secretaireId);
        recu.setStatut("DEMANDE");
        return this.create(recu);
    }
    public Recu genererRecuPaiement(Long paiementId, UUID secretaireId, String cheminFichier) {
        Recu recu = new Recu();
        recu.setPaiementId(paiementId);
        recu.setDemandePar(secretaireId);
        recu.setCheminFichier(cheminFichier);
        recu.setStatut("DISPONIBLE");
        return this.create(recu);
    }
    public Recu demanderRecuNumerise(Long paiementId, UUID directeurId) {
        Recu recu = new Recu();
        recu.setPaiementId(paiementId);
        recu.setDemandePar(directeurId);
        recu.setStatut("DEMANDE");
        return this.create(recu);
    }
    public Recu traiterRecuDemande(Long recuId, UUID secretaireId, String cheminFichier) {
        return recuRepository.findById(recuId).map(recu -> {
            recu.setTraitePar(secretaireId);
            recu.setCheminFichier(cheminFichier);
            recu.setStatut("DISPONIBLE");
            return recuRepository.save(recu);
        }).orElseThrow(() -> new IllegalArgumentException("Reçu non trouvé"));
    }
    public List<Recu> obtenirRecusParPaiement(Long paiementId) {
        return this.findByPaiementId(paiementId);
    }
}
