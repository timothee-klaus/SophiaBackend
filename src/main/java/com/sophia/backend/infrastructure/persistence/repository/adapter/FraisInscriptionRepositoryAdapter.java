package com.sophia.backend.infrastructure.persistence.repository.adapter;

import com.sophia.backend.domain.model.FraisInscription;
import com.sophia.backend.domain.repository.FraisInscriptionRepository;
import com.sophia.backend.infrastructure.persistence.entity.FraisInscriptionEntity;
import com.sophia.backend.infrastructure.persistence.mapper.FraisInscriptionMapper;
import com.sophia.backend.infrastructure.persistence.repository.FraisInscriptionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class FraisInscriptionRepositoryAdapter implements FraisInscriptionRepository {

    private final FraisInscriptionJpaRepository jpaRepository;
    private final FraisInscriptionMapper mapper;

    @Override
    public Optional<FraisInscription> findByUuid(UUID uuid) {
        return jpaRepository.findByUuid(uuid).map(mapper::toDomain);
    }

    @Override
    public List<FraisInscription> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<FraisInscription> findByCycleUuid(UUID cycleUuid) {
        return jpaRepository.findByCycleUuid(cycleUuid).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<FraisInscription> findByAnneeScolaireUuid(UUID anneeScolaireUuid) {
        return jpaRepository.findByAnneeScolaireUuid(anneeScolaireUuid).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public FraisInscription save(FraisInscription frais) {
        FraisInscriptionEntity entity = mapper.toEntity(frais);
        FraisInscriptionEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        jpaRepository.deleteByUuid(uuid);
    }
}
