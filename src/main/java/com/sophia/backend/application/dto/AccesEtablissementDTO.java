package com.sophia.backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccesEtablissementDTO {
    private Long id;
    private UUID utilisateurId;
    private Long etablissementId;
    private LocalDateTime createdAt;
}
