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
@Table("stage")
public class Stage {

    @Id
    private Long id;

    @Column("titre")
    private String titre;

    @Column("description")
    private String description;

    @Column("entreprise")
    private String entreprise;

    @Column("entrepriseId")
    private String entrepriseId;

    @Column("lieu")
    private String lieu;

    @Column("date_debut")
    private LocalDate dateDebut;

    @Column("date_fin")
    private LocalDate dateFin;

    @Column("remuneration")
    private Double remuneration;

    @Column("contact_email")
    private String contactEmail;

    @Column("created_at")
    private LocalDate createdAt;

    @Column("updated_at")
    private LocalDate updatedAt;

    @Column("type")
    private String type;

    @Column("technologies")
    private String technologies;

    @Column("duree")
    private String duree;

    @Column("competences")
    private String competences;

    @Column("niveauEtude")
    private String niveauEtude;
}
