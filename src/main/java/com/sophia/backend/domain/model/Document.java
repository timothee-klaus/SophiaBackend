package com.sophia.backend.domain.model;

import com.sophia.backend.domain.enums.TypeDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {
    private Long id;
    private UUID eleveId;
    private TypeDocument typeDocument;
    private String nomFichier;
    private String cheminFichier;
    private LocalDateTime dateUpload;
    private UUID utilisateurId;
    private String description;
    private LocalDateTime createdAt;
}
