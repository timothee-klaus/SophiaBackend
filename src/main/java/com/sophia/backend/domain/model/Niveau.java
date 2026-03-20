package com.sophia.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Niveau {
    private Long id;
    private UUID uid;
    private Long cycleId;
    private UUID cycleUid;
    private UUID cycleUuid;
    private Long etablissementId;
    private String nom;
    private Integer ordre;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Alias pour compatibilité (uuid <-> uid)
    public UUID getUuid() { return uid; }
    public void setUuid(UUID uuid) { this.uid = uuid; }
}
