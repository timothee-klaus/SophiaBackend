package com.sophia.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Niveau {
    private Long id;
    private String nom;
    private Long cycleId;
    private Long etablissementId;
    private Integer ordre;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
