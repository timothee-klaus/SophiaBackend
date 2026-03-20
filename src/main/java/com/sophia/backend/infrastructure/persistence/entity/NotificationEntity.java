package com.sophia.backend.infrastructure.persistence.entity;

import com.sophia.backend.domain.enums.TypeNotification;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notification")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NotificationEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uuid = UUID.randomUUID();

    @Enumerated(EnumType.STRING)
    private TypeNotification type;

    @Column(name = "expediteur_id")
    private UUID expediteurUuid;

    @Column(name = "destinataire_id")
    private UUID destinataireUuid;

    @Column(columnDefinition = "text")
    private String contenu;

    private boolean lu;
    private LocalDateTime dateCreation;
    private String lien;
    private LocalDateTime createdAt;
}
