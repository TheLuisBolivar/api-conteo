package com.conteo.api.models.dtos.markov;

import lombok.Data;

@Data
public class MarkovReqDto {
    private double[][] matrixMarkov;
    private double[][] initialState;
    private Integer n;
}
