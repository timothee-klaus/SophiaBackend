package com.sophia.backend.application.service;
import com.sophia.backend.domain.model.Blocage;
import com.sophia.backend.domain.repository.BlocageRepository;
import com.sophia.backend.domain.enums.TypeBlocage;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class BlocageService {
    private final BlocageRepository blocageRepository;
    public BlocageService(BlocageRepository blocageRepository) {
        this.blocageRepository = blocageRepository;
    }
    public Optional<Blocage> findById(Long id) {
        return blocageRepository.findById(id);
    }
    public List<Blocage> findAll() {
        return blocageRepository.findAll();
    }
    public List<Blocage> findByInscriptionId(Long inscriptionId) {
        return blocageRepository.findByInscriptionId(inscriptionId);
    }
    public List<Blocage> findActiveBlocks() {
        return blocageRepository.findActiveBlocks();
    }
    public Blocage create(Blocage blocage) {
        return blocageRepository.save(blocage);
    }
    public Blocage update(Blocage blocage) {
        return blocageRepository.save(blocage);
    }
    public void delete(Long id) {
        blocageRepository.deleteById(id);
    }
    public Blocage bloquerInscription(Long inscriptionId, String typeBlocage, String raison) {
        Blocage blocage = new Blocage();
        blocage.setInscriptionId(inscriptionId);
        blocage.setTypeBlocage(TypeBlocage.valueOf(typeBlocage));
        blocage.setRaison(raison);
        blocage.setEstActif(true);
        return this.create(blocage);
    }
    public void leverBlocage(Long blocageId, UUID utilisateurId) {
        blocageRepository.findById(blocageId).ifPresent(blocage -> {
            blocage.setEstActif(false);
            blocage.setLevePar(utilisateurId);
            blocageRepository.save(blocage);
        });
    }
    public boolean estBloquee(Long inscriptionId) {
        return this.findByInscriptionId(inscriptionId).stream()
                .anyMatch(Blocage::isEstActif);
    }
}
