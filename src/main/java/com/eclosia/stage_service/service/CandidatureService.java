package com.eclosia.stage_service.service;

import com.eclosia.stage_service.dto.CandidatureDto;
import com.eclosia.stage_service.dto.StudentCandidatureDto;
import com.eclosia.stage_service.entity.Candidature;
import com.eclosia.stage_service.entity.Stage;
import com.eclosia.stage_service.repository.StageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.eclosia.stage_service.repository.CandidatureRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CandidatureService {


    private final CandidatureRepository candidatureRepo;

    private final StageRepository stageRepo;


    public Mono<Candidature> createCandidature(CandidatureDto dto) {

        return candidatureRepo.save(mapToEntity(dto));
    }

    public Flux<StudentCandidatureDto> getCandidatureByStudentId(Long id) {
        return candidatureRepo.findByEtudiantId(id)
                .flatMap(candidature -> {
                    return stageRepo.findById(candidature.getStageId())
                            .map(stage -> toDto(candidature, stage));

                });
    }

    private StudentCandidatureDto toDto(Candidature candidature, Stage stage) {
        StudentCandidatureDto dto = new StudentCandidatureDto();

        dto.setId(candidature.getId());
        dto.setStageId(stage.getId());
        dto.setEtudiantId(candidature.getEtudiantId());
        dto.setEntrepriseId(stage.getEntrepriseId());
        dto.setEntreprise(stage.getEntreprise());
        dto.setTitre(stage.getTitre());
        dto.setStatut(candidature.getStatut());
        dto.setCvPath(candidature.getCv_path());
        dto.setMotivationPath(candidature.getMotivation_path());
        dto.setDateCandidature(candidature.getDate_candidature());

        return dto;

    }

    public Mono<Boolean> deleteCandidature(Long id) {
        return candidatureRepo.deleteById(id)
                .then(Mono.just(true))
                .onErrorResume(e -> {
                    // Log the error or handle it as needed
                    return Mono.just(false);
                });
    }

    private Candidature mapToEntity(CandidatureDto dto) {
        return Candidature.builder()
                .cv_path(dto.getCv_path())
                .motivation_path(dto.getMotivation_path())
                .etudiantId(dto.getEtudiantid())
                .stageId(dto.getStageid())
                .entrepriseId(dto.getEntrepriseid())
                .build();
    }


    public Flux<StudentCandidatureDto> getCandidatureByeEtrepriseId(Long id) {
        return candidatureRepo.findByEntrepriseId(id)
                .flatMap(candidature -> {
                    return stageRepo.findById(candidature.getStageId())
                            .map(stage -> toDto(candidature, stage));

                });
    }

    public Mono<Candidature> accepterCandidature(Long id) {
        return candidatureRepo.findById(id)
                .flatMap(candidature -> {
                    candidature.setStatut("accepte");
                    return candidatureRepo.save(candidature);
                });
    }

    public Mono<Candidature> rejeterCandidature(Long id) {
        return candidatureRepo.findById(id)
                .flatMap(candidature -> {
                    candidature.setStatut("refuse");
                    return candidatureRepo.save(candidature);
                });
    }
}
