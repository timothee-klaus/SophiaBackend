package com.sophia.backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {
    private Long id;
    private UUID eleveId;
    private String typeDocument;
    private String nomFichier;
    private String cheminFichier;
    private LocalDateTime dateUpload;
    private UUID utilisateurId;
    private String description;
    private LocalDateTime createdAt;
}
