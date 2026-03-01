package com.sophia.backend.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "acces_etablissement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccesEtablissementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID utilisateurId;
    private Long etablissementId;
    private LocalDateTime createdAt;
}

