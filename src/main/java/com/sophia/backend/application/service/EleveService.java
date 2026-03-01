package com.sophia.backend.application.service;

import com.sophia.backend.domain.model.Eleve;
import com.sophia.backend.domain.repository.EleveRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EleveService {
    private final EleveRepository eleveRepository;

    public EleveService(EleveRepository eleveRepository) {
        this.eleveRepository = eleveRepository;
    }

    public Optional<Eleve> findById(UUID id) {
        return eleveRepository.findById(id);
    }

    public Optional<Eleve> findByMatricule(String matricule) {
        return eleveRepository.findByMatricule(matricule);
    }

    public List<Eleve> findAll() {
        return eleveRepository.findAll();
    }

    public Eleve create(Eleve eleve) {
        return eleveRepository.save(eleve);
    }

    public Eleve update(Eleve eleve) {
        return eleveRepository.save(eleve);
    }

    public void delete(UUID id) {
        eleveRepository.deleteById(id);
    }
}

