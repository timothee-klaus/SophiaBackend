package com.sophia.backend.domain.model;

import com.sophia.backend.domain.enums.ModePaiement;
import com.sophia.backend.domain.enums.TypePaiement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paiement {
    private Long id;
    private Long inscription_id;
    private TypePaiement type_paiement;
    private Long reference_id;
    private BigDecimal montant;
    private LocalDateTime date_paiement;
    private ModePaiement mode_paiement;
    private String recu_path;
    private String commentaire;
    private UUID utilisateur_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}

