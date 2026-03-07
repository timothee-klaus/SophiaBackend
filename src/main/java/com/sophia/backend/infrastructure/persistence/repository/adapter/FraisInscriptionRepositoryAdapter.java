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
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class FraisInscriptionRepositoryAdapter implements FraisInscriptionRepository {
    private final FraisInscriptionJpaRepository jpaRepository;
    private final FraisInscriptionMapper mapper;

    @Override
    public Optional<FraisInscription> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<FraisInscription> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<FraisInscription> findByCycleId(Long cycleId) {
        return jpaRepository.findByCycleId(cycleId).stream()
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
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public List<FraisInscription> findByAnneeScolaireId(Long anneeScolaireId) {
        return jpaRepository.findByAnneeScolaireId(anneeScolaireId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
