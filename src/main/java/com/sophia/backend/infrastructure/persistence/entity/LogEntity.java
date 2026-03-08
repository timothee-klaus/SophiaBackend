package com.sophia.backend.infrastructure.persistence.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.sophia.backend.domain.enums.ActionLog;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LogEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID utilisateurId;

    @Enumerated(EnumType.STRING)
    private ActionLog action;

    private String entite;
    private String entiteId;

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode anciennesValeurs;

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode nouvellesValeurs;

    private String adresseIp;
    private String userAgent;
    private LocalDateTime dateAction;
    private String description;
}

