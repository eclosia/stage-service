package com.eclosia.stage_service.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StageRequest {

    private String titre;

    private String entreprise;

    private String entrepriseId;

    private String contact_email;

    private String lieu;

    private String type;

    private String description;

    private String technologies;

    private String duree;

    private LocalDate debut;

    private Double remuneration;

    private String competences;

    private String niveauEtude;

}
