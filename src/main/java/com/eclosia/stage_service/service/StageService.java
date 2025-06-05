package com.eclosia.stage_service.service;
import com.eclosia.stage_service.dto.StageDto;
import com.eclosia.stage_service.dto.StageRequest;
import com.eclosia.stage_service.exception.StageNotFoundException;
import com.eclosia.stage_service.entity.Stage;
import com.eclosia.stage_service.repository.StageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class StageService {
  private final StageRepository stageRepository;

  public Flux<StageDto> getAllStages() {
    return stageRepository.findAll()
       .map(this::mapToDto);
  }

  public Mono<StageDto> getStageById(Long id) {
    return stageRepository.findById(id)
       .map(this::mapToDto)
       .switchIfEmpty(Mono.error(new StageNotFoundException("Stage non trouvé avec l'ID: " + id)));
  }

  public Mono<StageDto> createStage(StageRequest stageRequest) {
    Stage stage = mapToEntity(stageRequest);
    stage.setCreatedAt(LocalDate.now());
    stage.setUpdatedAt(LocalDate.now());

    return stageRepository.save(stage)
       .map(this::mapToDto);
  }

  public Mono<StageDto> updateStage(Long id, StageRequest stageRequest) {
    return stageRepository.findById(id)
       .switchIfEmpty(Mono.error(new StageNotFoundException("Stage non trouvé avec l'ID: " + id)))
       .flatMap(existingStage -> {
         Stage updatedStage = mapToEntity(stageRequest);
         updatedStage.setId(existingStage.getId());
         updatedStage.setCreatedAt(existingStage.getCreatedAt());
         updatedStage.setUpdatedAt(LocalDate.now());

         return stageRepository.save(updatedStage);
       })
       .map(this::mapToDto);
  }

  public Mono<Void> deleteStage(Long id) {
    return stageRepository.findById(id)
       .switchIfEmpty(Mono.error(new StageNotFoundException("Stage non trouvé avec l'ID: " + id)))
       .flatMap(stage -> stageRepository.delete(stage));
  }

  public Flux<StageDto> searchStages(String keyword) {
    if (keyword == null || keyword.isEmpty()) {
      return getAllStages();
    }

    return Flux.concat(
          stageRepository.findByTitreContainingIgnoreCase(keyword),
          stageRepository.findByEntrepriseContainingIgnoreCase(keyword)
       )
       .distinct(Stage::getId)
       .map(this::mapToDto);
  }

  private StageDto mapToDto(Stage stage) {
    return StageDto.builder()
       .id(stage.getId())
       .titre(stage.getTitre())
       .description(stage.getDescription())
       .entreprise(stage.getEntreprise())
       .lieu(stage.getLieu())
       .dateDebut(stage.getDateDebut())
       .dateFin(stage.getDateFin())
       .remuneration(stage.getRemuneration())
       .contactEmail(stage.getContactEmail())
       .createdAt(stage.getCreatedAt())
       .updatedAt(stage.getUpdatedAt())
       .build();
  }

  private Stage mapToEntity(StageRequest request) {
    return Stage.builder()
       .titre(request.getTitre())
       .description(request.getDescription())
       .entreprise(request.getEntreprise())
       .lieu(request.getLieu())
       .dateDebut(request.getDateDebut())
       .dateFin(request.getDateFin())
       .remuneration(request.getRemuneration())
       .contactEmail(request.getContactEmail())
       .build();
  }
}
