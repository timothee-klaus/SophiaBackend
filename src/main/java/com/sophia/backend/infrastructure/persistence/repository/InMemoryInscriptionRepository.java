package com.sophia.backend.infrastructure.persistence.repository;

import com.sophia.backend.domain.model.Inscription;
import com.sophia.backend.domain.repository.InscriptionRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class InMemoryInscriptionRepository implements InscriptionRepository {
    private final Map<Long, Inscription> store = new ConcurrentHashMap<>();
    private final AtomicLong idGen = new AtomicLong(1);

    @Override
    public Optional<Inscription> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Inscription> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<Inscription> findByEleveId(UUID eleveId) {
        return store.values().stream()
                .filter(i -> Objects.equals(i.getEleveId(), eleveId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Inscription> findByNiveauId(Long niveauId) {
        return store.values().stream()
                .filter(i -> Objects.equals(i.getNiveauId(), niveauId))
                .collect(Collectors.toList());
    }

    @Override
    public Inscription save(Inscription inscription) {
        if (inscription.getId() == null) {
            inscription.setId(idGen.getAndIncrement());
        }
        store.put(inscription.getId(), inscription);
        return inscription;
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}

