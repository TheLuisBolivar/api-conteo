package com.conteo.api.models.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ProblemDtoRq {

    private Boolean entranTodos;
    private Boolean importaOrden;
    private Boolean seRepite;
    private Boolean esCircular;

    private List<Integer> listVariablesToRepeat;

    private int m;
    private int n;
}
