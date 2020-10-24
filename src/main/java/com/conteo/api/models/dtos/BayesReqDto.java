package com.conteo.api.models.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BayesReqDto {
    private List<BigDecimal> bIntersectionAi;
    private List<List<BigDecimal>> valuesProbabilityB;
}
