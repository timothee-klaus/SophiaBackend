package com.sophia.backend.infrastructure.persistence.repository.adapter;

import com.sophia.backend.domain.enums.ActionLog;
import com.sophia.backend.domain.model.Log;
import com.sophia.backend.domain.repository.LogRepository;
import com.sophia.backend.infrastructure.persistence.entity.LogEntity;
import com.sophia.backend.infrastructure.persistence.mapper.LogEntityMapper;
import com.sophia.backend.infrastructure.persistence.repository.LogJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class LogRepositoryAdapter implements LogRepository {
    private final LogJpaRepository jpaRepository;
    private final LogEntityMapper mapper;

    @Override
    public Optional<Log> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Log> findByUuid(UUID uuid) {
        return jpaRepository.findByUuid(uuid).map(mapper::toDomain);
    }


    @Override
    public Log save(Log log) {
        LogEntity entity = mapper.toEntity(log);
        LogEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public List<Log> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Log> findByUtilisateurId(UUID utilisateurUuid) {
        return jpaRepository.findByUtilisateurId(utilisateurUuid).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Log> findByEntite(String entite) {
        return jpaRepository.findByEntite(entite).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Log> findByEntiteAndEntiteId(String entite, String entiteId) {
        return jpaRepository.findByEntiteAndEntiteId(entite, entiteId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Log> findByAction(ActionLog action) {
        return jpaRepository.findByAction(action).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        jpaRepository.deleteByUuid(uuid);
    }
}
