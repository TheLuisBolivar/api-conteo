package com.conteo.api.services.bayes.service;

import com.conteo.api.models.dtos.bayes.BayesReqDto;
import com.conteo.api.models.dtos.bayes.BayesResDto;

public interface BayesService {

    BayesResDto calculate(BayesReqDto bayesReqDto) throws Exception;
}
