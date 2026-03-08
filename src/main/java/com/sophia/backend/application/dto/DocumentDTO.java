package com.sophia.backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DocumentDTO extends BaseDTO {
    private Long id;
    private UUID eleveId;
    private String typeDocument;
    private String nomFichier;
    private String cheminFichier;
    private LocalDateTime dateUpload;
    private UUID utilisateurId;
    private String description;
}
