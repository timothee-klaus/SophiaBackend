package com.sophia.backend.infrastructure.persistence.entity;

import com.sophia.backend.domain.enums.TypeBlocage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "blocages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BlocageEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long inscriptionId;

    @Enumerated(EnumType.STRING)
    private TypeBlocage typeBlocage;

    private String raison;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private boolean estActif;
    private UUID levePar;
    private LocalDateTime dateLevee;
    private LocalDateTime createdAt;
}

