package com.eclosia.stage_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidatures")
public class Candidature {

    @Id
    private Long id;

    @Column("stageId")
    private Long stageId;

    @Column("etudiantId")
    private Long etudiantId;

    @Column("entrepriseId")
    private Long entrepriseId;

    private String statut;

    private String cv_path;

    private String motivation_path;

    private LocalDate date_candidature = LocalDate.now();

}