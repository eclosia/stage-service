package com.eclosia.stage_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidatureDto {

    private String cv_path;

    private String motivation_path;

    private Long etudiantid;

    private Long stageid;

    private Long entrepriseid;

}
