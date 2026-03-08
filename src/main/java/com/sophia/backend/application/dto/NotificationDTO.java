package com.sophia.backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NotificationDTO extends BaseDTO {
    private Long id;
    private String type;
    private UUID expediteurId;
    private UUID destinataireId;
    private String contenu;
    private boolean lu;
    private LocalDateTime dateCreation;
    private String lien;
}

