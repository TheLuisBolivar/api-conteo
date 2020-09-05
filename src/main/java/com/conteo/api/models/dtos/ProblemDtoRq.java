package com.conteo.api.models.dtos;

import lombok.Data;

@Data
public class ProblemDtoRq {

    private Boolean entranTodos;
    private Boolean importaOrden;
    private Boolean seRepite;
    private Boolean esCircular;

    private int m;
    private int n;

}
