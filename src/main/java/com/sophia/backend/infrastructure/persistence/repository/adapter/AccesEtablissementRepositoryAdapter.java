package com.sophia.backend.infrastructure.persistence.repository.adapter;

import com.sophia.backend.domain.model.AccesEtablissement;
import com.sophia.backend.domain.repository.AccesEtablissementRepository;
import com.sophia.backend.infrastructure.persistence.entity.AccesEtablissementEntity;
import com.sophia.backend.infrastructure.persistence.mapper.AccesEtablissementMapper;
import com.sophia.backend.infrastructure.persistence.repository.AccesEtablissementJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AccesEtablissementRepositoryAdapter implements AccesEtablissementRepository {
    private final AccesEtablissementJpaRepository jpaRepository;
    private final AccesEtablissementMapper mapper;

    @Override
    public Optional<AccesEtablissement> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<AccesEtablissement> findByUtilisateurId(UUID utilisateurId) {
        return jpaRepository.findByUtilisateurId(utilisateurId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<AccesEtablissement> findByEtablissementId(Long etablissementId) {
        return jpaRepository.findByEtablissementId(etablissementId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public AccesEtablissement save(AccesEtablissement acces) {
        AccesEtablissementEntity entity = new AccesEtablissementEntity();
        entity.setId(acces.getId());
        entity.setUtilisateurId(acces.getUtilisateurId());
        entity.setEtablissementId(acces.getEtablissementId());
        entity.setCreatedAt(acces.getCreatedAt());
        AccesEtablissementEntity saved = jpaRepository.save(entity);
        AccesEtablissement result = new AccesEtablissement();
        result.setId(saved.getId());
        result.setUtilisateurId(saved.getUtilisateurId());
        result.setEtablissementId(saved.getEtablissementId());
        result.setCreatedAt(saved.getCreatedAt());
        return result;
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}



