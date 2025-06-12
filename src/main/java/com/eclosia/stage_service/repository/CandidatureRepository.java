package com.eclosia.stage_service.repository;

import com.eclosia.stage_service.entity.Candidature;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface CandidatureRepository extends R2dbcRepository<Candidature, Long> {

    Flux<Candidature> findByEtudiantId(Long id);

    Flux<Candidature> findByEntrepriseId(Long id);

}