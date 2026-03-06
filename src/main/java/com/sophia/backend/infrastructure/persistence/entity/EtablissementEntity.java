package com.sophia.backend.infrastructure.persistence.entity;

import com.sophia.backend.domain.enums.StatutEtablissement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "etablissements")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtablissementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String adresse;
    private String telephone;
    private String email;
    private String logo;

    private LocalDate dateCreation;

    @Enumerated(EnumType.STRING)
    private StatutEtablissement statut;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

