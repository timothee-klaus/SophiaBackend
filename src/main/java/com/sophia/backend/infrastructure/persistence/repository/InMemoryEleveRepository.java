package com.sophia.backend.infrastructure.persistence.repository;

import com.sophia.backend.domain.model.Eleve;
import com.sophia.backend.domain.repository.EleveRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class InMemoryEleveRepository implements EleveRepository {
    private final Map<UUID, Eleve> store = new ConcurrentHashMap<>();

    @Override
    public Optional<Eleve> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Eleve> findByMatricule(String matricule) {
        return store.values().stream()
                .filter(e -> Objects.equals(e.getMatricule(), matricule))
                .findFirst();
    }

    @Override
    public List<Eleve> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<Eleve> findByEtablissementId(Long etablissementId) {
        return store.values().stream()
                .filter(e -> Objects.equals(e.getEtablissementId(), etablissementId))
                .collect(Collectors.toList());
    }

    @Override
    public Eleve save(Eleve eleve) {
        if (eleve.getId() == null) {
            eleve.setId(UUID.randomUUID());
        }
        store.put(eleve.getId(), eleve);
        return eleve;
    }

    @Override
    public void deleteById(UUID id) {
        store.remove(id);
    }
}

