package com.sophia.backend.infrastructure.persistence.repository.adapter;

import com.sophia.backend.domain.model.FraisScolaire;
import com.sophia.backend.domain.repository.FraisScolaireRepository;
import com.sophia.backend.infrastructure.persistence.entity.FraisScolaireEntity;
import com.sophia.backend.infrastructure.persistence.mapper.FraisScolaireMapper;
import com.sophia.backend.infrastructure.persistence.repository.FraisScolaireJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class FraisScolaireRepositoryAdapter implements FraisScolaireRepository {
    private final FraisScolaireJpaRepository jpaRepository;
    private final FraisScolaireMapper mapper;

    @Override
    public Optional<FraisScolaire> findByUuid(UUID uuid) {
        return jpaRepository.findByUuid(uuid).map(mapper::toDomain);
    }

    @Override
    public List<FraisScolaire> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<FraisScolaire> findByNiveauUuid(UUID niveauUuid) {
        return jpaRepository.findByNiveauUuid(niveauUuid).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public FraisScolaire save(FraisScolaire frais) {
        FraisScolaireEntity entity = mapper.toEntity(frais);
        FraisScolaireEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        jpaRepository.deleteByUuid(uuid);
    }

    @Override
    public List<FraisScolaire> findByAnneeScolaireUuid(UUID anneeScolaireUuid) {
        return jpaRepository.findByAnneeScolaireUuid(anneeScolaireUuid).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
