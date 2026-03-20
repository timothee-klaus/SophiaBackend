package com.sophia.backend.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sophia.backend.domain.enums.TypeNotification;
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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID uuid;
    private TypeNotification type;
    private UUID expediteurUuid;
    private UUID destinataireUuid;
    private String contenu;
    private boolean lu;
    private LocalDateTime dateCreation;
    private String lien;
}
