package com.sophia.backend.application.service;
import com.sophia.backend.domain.model.AccesEtablissement;
import com.sophia.backend.domain.repository.AccesEtablissementRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class AccesEtablissementService {
    private final AccesEtablissementRepository accesEtablissementRepository;
    public AccesEtablissementService(AccesEtablissementRepository accesEtablissementRepository) {
        this.accesEtablissementRepository = accesEtablissementRepository;
    }
    public Optional<AccesEtablissement> findById(Long id) {
        return accesEtablissementRepository.findById(id);
    }
    public List<AccesEtablissement> findByUtilisateurId(UUID utilisateurId) {
        return accesEtablissementRepository.findByUtilisateurId(utilisateurId);
    }
    public List<AccesEtablissement> findByEtablissementId(Long etablissementId) {
        return accesEtablissementRepository.findByEtablissementId(etablissementId);
    }
    public AccesEtablissement create(AccesEtablissement acces) {
        return accesEtablissementRepository.save(acces);
    }
    public void delete(Long id) {
        accesEtablissementRepository.deleteById(id);
    }
    public AccesEtablissement accorderAcces(UUID utilisateurId, Long etablissementId) {
        AccesEtablissement acces = new AccesEtablissement();
        acces.setUtilisateurId(utilisateurId);
        acces.setEtablissementId(etablissementId);
        return this.create(acces);
    }
    public void revoquerAcces(Long id) {
        this.delete(id);
    }
}
