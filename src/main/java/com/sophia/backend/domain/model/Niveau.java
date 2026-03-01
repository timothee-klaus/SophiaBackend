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
    private Long cycle_id;
    private Long etablissement_id;
    private Integer ordre;
    private LocalDateTime created_at;
}

