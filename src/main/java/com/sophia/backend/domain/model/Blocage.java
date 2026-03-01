package com.sophia.backend.domain.model;

import com.sophia.backend.domain.enums.TypeBlocage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blocage {
    private Long id;
    private Long inscription_id;
    private TypeBlocage type_blocage;
    private String raison;
    private LocalDate date_debut;
    private LocalDate date_fin; // null si actif
    private boolean est_actif;
    private UUID leve_par;
    private LocalDateTime date_levee;
    private LocalDateTime created_at;
}

