package com.conteo.api.models.dtos.bayes;

import com.sun.istack.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BayesReqDto {
   @NotNull private List<BigDecimal> x;
   @NotNull private List<List<BigDecimal>> valuesProbabilityB;
}
