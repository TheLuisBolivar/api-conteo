package com.conteo.api.models.dtos.bayes;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BayesResDto {
    BigDecimal result;
    String description;
}
