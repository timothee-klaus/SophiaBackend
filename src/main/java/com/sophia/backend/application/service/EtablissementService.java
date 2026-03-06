package com.sophia.backend.application.service;
import com.sophia.backend.domain.model.Etablissement;
import com.sophia.backend.domain.repository.EtablissementRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class EtablissementService {
    private final EtablissementRepository etablissementRepository;
    public EtablissementService(EtablissementRepository etablissementRepository) {
        this.etablissementRepository = etablissementRepository;
    }
    public Optional<Etablissement> findById(Long id) {
        return etablissementRepository.findById(id);
    }
    public List<Etablissement> findAll() {
        return etablissementRepository.findAll();
    }
    public List<Etablissement> findByStatut(String statut) {
        return etablissementRepository.findByStatut(statut);
    }
    public Etablissement create(Etablissement etablissement) {
        return etablissementRepository.save(etablissement);
    }
    public Etablissement update(Etablissement etablissement) {
        return etablissementRepository.save(etablissement);
    }
    public void delete(Long id) {
        etablissementRepository.deleteById(id);
    }
    public Etablissement creerEtablissement(Etablissement etablissement) {
        return this.create(etablissement);
    }
    public Etablissement modifierEtablissement(Long id, Etablissement etablissementUpdated) {
        return etablissementRepository.findById(id).map(existant -> {
            existant.setNom(etablissementUpdated.getNom());
            existant.setAdresse(etablissementUpdated.getAdresse());
            existant.setTelephone(etablissementUpdated.getTelephone());
            existant.setEmail(etablissementUpdated.getEmail());
            existant.setLogo(etablissementUpdated.getLogo());
            existant.setStatut(etablissementUpdated.getStatut());
            return etablissementRepository.save(existant);
        }).orElseThrow(() -> new IllegalArgumentException("Établissement non trouvé"));
    }
    public void supprimerEtablissement(Long id) {
        this.delete(id);
    }
}
