package com.conteo.api.models.dtos.conteo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProblemDtoRs {

    private BigDecimal resultado;
    private String description;
}
