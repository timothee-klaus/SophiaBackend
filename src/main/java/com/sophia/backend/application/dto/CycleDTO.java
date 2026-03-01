package com.sophia.backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CycleDTO {
    private Long id;
    private String nom;
    private String description;
    private Integer ordre;
    private LocalDateTime createdAt;
}
