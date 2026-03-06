package com.sophia.backend.infrastructure.persistence.entity;

import com.sophia.backend.domain.enums.TypeDocument;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "documents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID eleveId;

    @Enumerated(EnumType.STRING)
    private TypeDocument typeDocument;

    private String nomFichier;
    private String cheminFichier;
    private LocalDateTime dateUpload;
    private UUID utilisateurId;

    @Column(columnDefinition = "text")
    private String description;

    private LocalDateTime createdAt;
}

