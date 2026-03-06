package com.sophia.backend.infrastructure.persistence.repository.adapter;

import com.sophia.backend.domain.model.Niveau;
import com.sophia.backend.domain.repository.NiveauRepository;
import com.sophia.backend.infrastructure.persistence.entity.NiveauEntity;
import com.sophia.backend.infrastructure.persistence.mapper.NiveauEntityMapper;
import com.sophia.backend.infrastructure.persistence.repository.NiveauJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class NiveauRepositoryAdapter implements NiveauRepository {
    private final NiveauJpaRepository jpaRepository;
    private final NiveauEntityMapper mapper;

    @Override
    public Optional<Niveau> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Niveau> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Niveau> findByCycleId(Long cycleId) {
        return jpaRepository.findByCycleId(cycleId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Niveau> findByEtablissementId(Long etablissementId) {
        return jpaRepository.findByEtablissementId(etablissementId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Niveau save(Niveau niveau) {
        NiveauEntity entity = mapper.toEntity(niveau);
        NiveauEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}



