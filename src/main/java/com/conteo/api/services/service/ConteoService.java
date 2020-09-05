package com.conteo.api.services.service;

import com.conteo.api.models.dtos.ProblemDtoRq;
import com.conteo.api.models.dtos.ProblemDtoRs;

public interface ConteoService {

    ProblemDtoRs getPermutation(ProblemDtoRq problemDtoRq);
    ProblemDtoRs getPermutationCircular(ProblemDtoRq problemDtoRq);
    ProblemDtoRs getPermutationWithRepeat(ProblemDtoRq problemDtoRq);
    ProblemDtoRs getVariation(ProblemDtoRq problemDtoRq);
    ProblemDtoRs getVariationWithRepeat(ProblemDtoRq problemDtoRq);
    ProblemDtoRs getCombination(ProblemDtoRq problemDtoRq);
    ProblemDtoRs getCombinationWithRepeat(ProblemDtoRq problemDtoRq);
}
