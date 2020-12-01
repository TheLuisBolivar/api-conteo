package com.conteo.api.services.markov.service;

import com.conteo.api.models.dtos.markov.MarkovReqDto;
import com.conteo.api.models.dtos.markov.MarkovResDto;

public interface MarkovService {
    MarkovResDto calculate(MarkovReqDto markovReqDto) throws Exception;
}
