package com.sophia.backend.infrastructure.persistence.repository.adapter;

import com.sophia.backend.domain.model.Etablissement;
import com.sophia.backend.domain.repository.EtablissementRepository;
import com.sophia.backend.infrastructure.persistence.entity.EtablissementEntity;
import com.sophia.backend.infrastructure.persistence.mapper.EtablissementEntityMapper;
import com.sophia.backend.infrastructure.persistence.repository.EtablissementJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class EtablissementRepositoryAdapter implements EtablissementRepository {
    private final EtablissementJpaRepository jpaRepository;
    private final EtablissementEntityMapper mapper;

    @Override
    public Optional<Etablissement> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Etablissement> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Etablissement save(Etablissement etablissement) {
        EtablissementEntity entity = mapper.toEntity(etablissement);
        EtablissementEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}

