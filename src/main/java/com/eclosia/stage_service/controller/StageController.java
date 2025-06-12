package com.eclosia.stage_service.controller;
import com.eclosia.stage_service.dto.StageDto;
import com.eclosia.stage_service.dto.StageRequest;
import com.eclosia.stage_service.entity.Stage;
import com.eclosia.stage_service.service.StageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/stages")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4000")
@Tag(name = "Stage API", description = "API pour la gestion des stages")
public class StageController {

    private final StageService stageService;

    @GetMapping
    @Operation(summary = "Récupérer tous les stages")
    public Flux<StageDto> getAllStages(@RequestParam(required = false) String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return stageService.searchStages(keyword);
        }
        return stageService.getAllStages();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un stage par son ID")
    public Mono<StageDto> getStageById(@PathVariable Long id) {
        return stageService.getStageById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un nouveau stage")
    public Mono<Long> createStage(@Valid @RequestBody StageRequest stageRequest) {
        return stageService.createStage(stageRequest)
                .map(StageDto::getId);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un stage existant")
    public Mono<StageDto> updateStage(@PathVariable Long id, @Valid @RequestBody StageRequest stageRequest) {
        return stageService.updateStage(id, stageRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un stage")
    public Mono<Void> deleteStage(@PathVariable Long id) {
        return stageService.deleteStage(id);
    }

    @GetMapping("/enterprise/{entrepriseId}")
    @Operation(summary = "Récupérer les stages par ID d'entreprise")
    public Flux<Stage> getStagesByEnterpriseId(@PathVariable String entrepriseId) {
        return stageService.getStagesByEnterpriseId(entrepriseId);
    }

}