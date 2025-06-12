package com.eclosia.stage_service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentCandidatureDto {

    private Long id;

    private Long stageId;

    private Long etudiantId;

    private String entrepriseId;

    private String entreprise;

    private String titre;

    private String statut;

    private String cvPath;

    private String motivationPath;

    private LocalDate dateCandidature;


}
