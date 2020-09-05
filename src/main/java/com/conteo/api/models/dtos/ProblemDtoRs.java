package com.conteo.api.models.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProblemDtoRs {

    private Double resultado;
    private String description;
}
