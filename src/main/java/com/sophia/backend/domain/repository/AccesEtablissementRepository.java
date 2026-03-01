package com.sophia.backend.domain.repository;

import com.sophia.backend.domain.model.AccesEtablissement;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccesEtablissementRepository {
    Optional<AccesEtablissement> findById(Long id);
    List<AccesEtablissement> findByUtilisateurId(UUID utilisateurId);
    List<AccesEtablissement> findByEtablissementId(Long etablissementId);
    AccesEtablissement save(AccesEtablissement acces);
    void deleteById(Long id);
}

