package com.sophia.backend.application.service;

import com.sophia.backend.application.dto.TranchePaiementDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TranchePaiementService {

    public TranchePaiementDTO create(TranchePaiementDTO dto) {
        return dto;
    }

    public List<TranchePaiementDTO> findAll() {
        return List.of();
    }

    public List<TranchePaiementDTO> findByFraisUuid(UUID fraisUuid) {
        return List.of();
    }

    public TranchePaiementDTO findByUuid(UUID uuid) {
        return new TranchePaiementDTO();
    }

    public TranchePaiementDTO update(UUID uuid, TranchePaiementDTO dto) {
        return dto;
    }

    public void delete(UUID uuid) {
    }
}
