package com.sophia.backend.infrastructure.persistence.repository.adapter;

import com.sophia.backend.domain.model.Recu;
import com.sophia.backend.domain.repository.RecuRepository;
import com.sophia.backend.infrastructure.persistence.entity.RecuEntity;
import com.sophia.backend.infrastructure.persistence.mapper.RecuMapper;
import com.sophia.backend.infrastructure.persistence.repository.RecuJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class RecuRepositoryAdapter implements RecuRepository {
    private final RecuJpaRepository jpaRepository;
    private final RecuMapper mapper;

    @Override
    public Optional<Recu> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Recu> findByPaiementId(Long paiementId) {
        return jpaRepository.findByPaiementId(paiementId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Recu save(Recu recu) {
        RecuEntity entity = mapper.toEntity(recu);
        RecuEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}

