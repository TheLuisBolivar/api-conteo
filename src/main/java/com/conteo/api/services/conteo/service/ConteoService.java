package com.conteo.api.services.conteo.service;

import com.conteo.api.models.dtos.conteo.ProblemDtoRq;
import com.conteo.api.models.dtos.conteo.ProblemDtoRs;

public interface ConteoService {

    ProblemDtoRs getPermutation(ProblemDtoRq problemDtoRq);
    ProblemDtoRs getPermutationCircular(ProblemDtoRq problemDtoRq);
    ProblemDtoRs getPermutationWithRepeat(ProblemDtoRq problemDtoRq) throws Exception;
    ProblemDtoRs getVariation(ProblemDtoRq problemDtoRq);
    ProblemDtoRs getVariationWithRepeat(ProblemDtoRq problemDtoRq);
    ProblemDtoRs getCombination(ProblemDtoRq problemDtoRq);
    ProblemDtoRs getCombinationWithRepeat(ProblemDtoRq problemDtoRq);
}
