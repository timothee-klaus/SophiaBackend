package com.sophia.backend.domain.repository;
import com.sophia.backend.domain.model.Etablissement;
import java.util.List;
import java.util.Optional;
public interface EtablissementRepository {
    Optional<Etablissement> findById(Long id);
    List<Etablissement> findAll();
    List<Etablissement> findByStatut(String statut);
    Etablissement save(Etablissement etablissement);
    void deleteById(Long id);
}
