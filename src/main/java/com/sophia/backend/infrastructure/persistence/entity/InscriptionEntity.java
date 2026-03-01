package com.sophia.backend.infrastructure.persistence.entity;

import com.sophia.backend.domain.enums.StatutInscription;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "inscription")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID eleveId;
    private Long niveauId;
    private Long anneeScolaireId;
    private LocalDate dateInscription;

    @Enumerated(EnumType.STRING)
    private StatutInscription statut;

    @Column(columnDefinition = "text")
    private String commentaire;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

