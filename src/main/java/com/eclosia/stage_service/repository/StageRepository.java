package com.eclosia.stage_service.repository;
import com.eclosia.stage_service.entity.Stage;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface StageRepository extends R2dbcRepository<Stage, Long> {

  Flux<Stage> findByEntrepriseContainingIgnoreCase(String entreprise);

  Flux<Stage> findByTitreContainingIgnoreCase(String titre);
}
