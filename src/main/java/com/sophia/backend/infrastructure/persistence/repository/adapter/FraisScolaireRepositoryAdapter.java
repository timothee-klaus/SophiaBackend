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
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class FraisScolaireRepositoryAdapter implements FraisScolaireRepository {
    private final FraisScolaireJpaRepository jpaRepository;
    private final FraisScolaireMapper mapper;

    @Override
    public Optional<FraisScolaire> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<FraisScolaire> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<FraisScolaire> findByNiveauId(Long niveauId) {
        return jpaRepository.findByNiveauId(niveauId).stream()
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
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public List<FraisScolaire> findByAnneeScolaireId(Long anneeScolaireId) {
        return jpaRepository.findByAnneeScolaireId(anneeScolaireId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
