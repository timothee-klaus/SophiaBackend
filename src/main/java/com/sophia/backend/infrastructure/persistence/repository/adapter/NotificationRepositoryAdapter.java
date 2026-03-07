package com.sophia.backend.infrastructure.persistence.repository.adapter;

import com.sophia.backend.domain.model.Notification;
import com.sophia.backend.domain.repository.NotificationRepository;
import com.sophia.backend.infrastructure.persistence.entity.NotificationEntity;
import com.sophia.backend.infrastructure.persistence.mapper.NotificationMapper;
import com.sophia.backend.infrastructure.persistence.repository.NotificationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class NotificationRepositoryAdapter implements NotificationRepository {
    private final NotificationJpaRepository jpaRepository;
    private final NotificationMapper mapper;

    @Override
    public Optional<Notification> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Notification> findByDestinataireId(UUID destinataireId) {
        return jpaRepository.findByDestinataireId(destinataireId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Notification save(Notification notification) {
        NotificationEntity entity = mapper.toEntity(notification);
        NotificationEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public List<Notification> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Notification> findUnreadByDestinataireId(UUID destinataireId) {
        return jpaRepository.findByDestinataireIdAndLuFalse(destinataireId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}

