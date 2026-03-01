package com.sophia.backend.infrastructure.persistence.entity;

import com.sophia.backend.domain.enums.TypeNotification;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notification")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeNotification type;

    private UUID expediteurId;
    private UUID destinataireId;

    @Column(columnDefinition = "text")
    private String contenu;

    private boolean lu;
    private LocalDateTime dateCreation;
    private String lien;
    private LocalDateTime createdAt;
}

