package com.sophia.backend.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NiveauDTO extends BaseDTO {
    /**
     * UUID unique du niveau exposé à l'API (au lieu de l'ID interne)
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID uuid;
    
    private String nom;
    
    /**
     * UUID du cycle (exposé à l'API au lieu de l'ID numérique)
     */
    private UUID cycleUuid;
    
    private Integer ordre;
}
