package com.sophia.backend.infrastructure.persistence.repository.adapter;

import com.sophia.backend.domain.model.TranchePaiement;
import com.sophia.backend.domain.repository.TranchePaiementRepository;
import com.sophia.backend.infrastructure.persistence.entity.TranchePaiementEntity;
import com.sophia.backend.infrastructure.persistence.mapper.TranchePaiementMapper;
import com.sophia.backend.infrastructure.persistence.repository.TranchePaiementJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TranchePaiementRepositoryAdapter implements TranchePaiementRepository {
    private final TranchePaiementJpaRepository jpaRepository;
    private final TranchePaiementMapper mapper;

    @Override
    public Optional<TranchePaiement> findByUuid(UUID uuid) {
        return jpaRepository.findByUuid(uuid).map(mapper::toDomain);
    }

    @Override
    public List<TranchePaiement> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<TranchePaiement> findByFraisScolaireUuid(UUID fraisScolaireUuid) {
        return jpaRepository.findByFraisScolaireUuid(fraisScolaireUuid).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public TranchePaiement save(TranchePaiement tranche) {
        TranchePaiementEntity entity = mapper.toEntity(tranche);
        TranchePaiementEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        jpaRepository.deleteByUuid(uuid);
    }
}
