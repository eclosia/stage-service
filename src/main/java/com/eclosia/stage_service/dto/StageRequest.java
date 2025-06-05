package com.eclosia.stage_service.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
  @NotBlank(message = "Le titre est obligatoire")
  private String titre;

  @NotBlank(message = "La description est obligatoire")
  private String description;

  @NotBlank(message = "Le nom de l'entreprise est obligatoire")
  private String entreprise;

  @NotBlank(message = "Le lieu est obligatoire")
  private String lieu;

  @NotNull(message = "La date de début est obligatoire")
  private LocalDate dateDebut;

  @NotNull(message = "La date de fin est obligatoire")
  private LocalDate dateFin;

  private Double remuneration;

  @Email(message = "L'email de contact doit être valide")
  private String contactEmail;
}
