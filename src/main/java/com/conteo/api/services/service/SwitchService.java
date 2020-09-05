package com.conteo.api.services.service;

import com.conteo.api.models.dtos.ProblemDtoRq;
import com.conteo.api.models.dtos.ProblemDtoRs;

public interface SwitchService {

    ProblemDtoRs resolveCase(ProblemDtoRq problemDtoRq);
}
