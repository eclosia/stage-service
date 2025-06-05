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


public class StageDto {
  private Long id;
  private String titre;
  private String description;
  private String entreprise;
  private String lieu;
  private LocalDate dateDebut;
  private LocalDate dateFin;
  private Double remuneration;
  private String contactEmail;
  private LocalDate createdAt;
  private LocalDate updatedAt;
}
