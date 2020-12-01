package com.conteo.api.models.dtos.markov;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MarkovResDto {
    private Boolean status;
    private double[][] resultMatrix;
    private double[][] resultState;
    private String description;
}
