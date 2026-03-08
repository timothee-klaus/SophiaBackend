package com.sophia.backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NiveauDTO extends BaseDTO {
    private Long id;
    private String nom;
    private Long cycleId;
    private Long etablissementId;
    private Integer ordre;
}
