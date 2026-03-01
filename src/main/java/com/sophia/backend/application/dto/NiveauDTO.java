package com.sophia.backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NiveauDTO {
    private Long id;
    private String nom;
    private Long cycleId;
    private Long etablissementId;
    private Integer ordre;
    private LocalDateTime createdAt;
}
