package com.conteo.api.services.conteo.service;

import com.conteo.api.models.dtos.conteo.ProblemDtoRq;
import com.conteo.api.models.dtos.conteo.ProblemDtoRs;

public interface SwitchService {

    ProblemDtoRs resolveCase(ProblemDtoRq problemDtoRq) throws Exception;
}
