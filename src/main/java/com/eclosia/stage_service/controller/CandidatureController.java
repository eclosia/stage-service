package com.eclosia.stage_service.controller;

import com.eclosia.stage_service.dto.CandidatureDto;
import com.eclosia.stage_service.dto.StudentCandidatureDto;
import com.eclosia.stage_service.entity.Candidature;
import com.eclosia.stage_service.service.CandidatureService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/condidature")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4000")
@Tag(name = "Condidature API", description = "API pour la gestion des condidature")
public class CandidatureController {

    private static final Logger logger = LoggerFactory.getLogger(CandidatureController.class);
    private final CandidatureService condidatureService;

    @PostMapping
    public Mono<ResponseEntity<Candidature>> createCandidature(@RequestBody CandidatureDto dto) {

        return condidatureService.createCandidature(dto)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> {
                    return Mono.just(ResponseEntity.status(500).build());
                });
    }

    @GetMapping("/{id}")
    public Flux<StudentCandidatureDto> getCandidatureByStudentId(@PathVariable Long id) {
        return condidatureService.getCandidatureByStudentId(id);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Object>> deleteCandidature(@PathVariable Long id) {
        return condidatureService.deleteCandidature(id)
                .map(deleted -> ResponseEntity.noContent().build())
                .onErrorResume(e -> {
                    logger.error("Error deleting candidature with id {}: {}", id, e.getMessage());
                    return Mono.just(ResponseEntity.status(500).build());
                });
    }

    @GetMapping("/entreprise/{id}")
    public Flux<StudentCandidatureDto> getCandidatureByEntrepriseId(@PathVariable Long id) {
        return condidatureService.getCandidatureByeEtrepriseId(id);
    }

    @PutMapping("/accepte/{id}")
    public Mono<ResponseEntity<Candidature>> accepterCandidature(@PathVariable Long id) {
        return condidatureService.accepterCandidature(id)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> {
                    logger.error("Error accepting candidature with id {}: {}", id, e.getMessage());
                    return Mono.just(ResponseEntity.status(500).build());
                });
    }

    @PutMapping("/reject/{id}")
    public Mono<ResponseEntity<Candidature>> rejeterCandidature(@PathVariable Long id) {
        return condidatureService.rejeterCandidature(id)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> {
                    logger.error("Error rejecting candidature with id {}: {}", id, e.getMessage());
                    return Mono.just(ResponseEntity.status(500).build());
                });
    }

}
