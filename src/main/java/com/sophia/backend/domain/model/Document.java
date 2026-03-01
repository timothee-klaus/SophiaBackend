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
    private UUID eleve_id;
    private TypeDocument type_document;
    private String nom_fichier;
    private String chemin_fichier;
    private LocalDateTime date_upload;
    private UUID utilisateur_id;
    private String description;
    private LocalDateTime created_at;
}

