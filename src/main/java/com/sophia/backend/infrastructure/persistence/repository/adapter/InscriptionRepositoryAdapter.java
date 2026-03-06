package com.sophia.backend.infrastructure.persistence.repository.adapter;

import com.sophia.backend.domain.model.Inscription;
import com.sophia.backend.domain.repository.InscriptionRepository;
import com.sophia.backend.infrastructure.persistence.entity.InscriptionEntity;
import com.sophia.backend.infrastructure.persistence.mapper.InscriptionEntityMapper;
import com.sophia.backend.infrastructure.persistence.repository.InscriptionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class InscriptionRepositoryAdapter implements InscriptionRepository {
    private final InscriptionJpaRepository jpaRepository;
    private final InscriptionEntityMapper mapper;

    @Override
    public Optional<Inscription> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Inscription> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Inscription> findByEleveId(UUID eleveId) {
        return jpaRepository.findByEleveId(eleveId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Inscription> findByNiveauId(Long niveauId) {
        return jpaRepository.findByNiveauId(niveauId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Inscription save(Inscription inscription) {
        InscriptionEntity entity = mapper.toEntity(inscription);
        InscriptionEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}

