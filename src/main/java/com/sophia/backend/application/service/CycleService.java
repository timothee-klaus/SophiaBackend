package com.sophia.backend.application.service;
import com.sophia.backend.domain.model.Cycle;
import com.sophia.backend.domain.repository.CycleRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class CycleService {
    private final CycleRepository cycleRepository;
    public CycleService(CycleRepository cycleRepository) {
        this.cycleRepository = cycleRepository;
    }
    public Optional<Cycle> findById(Long id) {
        return cycleRepository.findById(id);
    }
    public List<Cycle> findAll() {
        return cycleRepository.findAll();
    }
    public Cycle create(Cycle cycle) {
        return cycleRepository.save(cycle);
    }
    public Cycle update(Cycle cycle) {
        return cycleRepository.save(cycle);
    }
    public void delete(Long id) {
        cycleRepository.deleteById(id);
    }
    public List<Cycle> obtenirCyclesDisponibles() {
        return this.findAll();
    }
}
