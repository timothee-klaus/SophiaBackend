package com.sophia.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccesEtablissement {
    private Long id;
    private UUID utilisateur_id;
    private Long etablissement_id;
    private LocalDateTime created_at;
}

