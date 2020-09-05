package com.conteo.api.services.impl;

import com.conteo.api.models.dtos.ProblemDtoRq;
import com.conteo.api.models.dtos.ProblemDtoRs;
import com.conteo.api.services.service.ConteoService;
import com.conteo.api.utils.ConteoConstants;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.LongStream;

@Service
public class ConteoServiceImpl implements ConteoService {

    private Long factorialMethod(int n){
        return LongStream.rangeClosed(1, n)
                .reduce(1, (long x, long y) -> x * y);
    }

    private ProblemDtoRs buildResult(long result, String description){
        ProblemDtoRs problemDtoRs = ProblemDtoRs.builder()
                .resultado(result)
                .description(description)
                .build();

        return problemDtoRs;
    }

    private Long getListFactorialResult(List<Integer> listRepeats){
        Long result = 1L;
        for(Integer i : listRepeats){
            result *= factorialMethod(i);
        }
        return result;
    }

    public ProblemDtoRs getPermutation(ProblemDtoRq problemDtoRq) {
        Long result = factorialMethod(problemDtoRq.getN());
        String description = ConteoConstants.SOLUCION +ConteoConstants.PERMUTACION;
        return buildResult(result, description);
    }

    public ProblemDtoRs getPermutationCircular(ProblemDtoRq problemDtoRq) {
        Long result = factorialMethod(problemDtoRq.getN() - 1);
        String description = ConteoConstants.SOLUCION +ConteoConstants.PERMUTACION_CIRCULAR;
        return buildResult(result, description);
    }

    public ProblemDtoRs getPermutationWithRepeat(ProblemDtoRq problemDtoRq) {
        Long numerator = factorialMethod(problemDtoRq.getN());
        Long denominator = getListFactorialResult(problemDtoRq.getListVariablesToRepeat());
        String description = ConteoConstants.SOLUCION + ConteoConstants.PERMUTACION_REPETICION;
        return buildResult((numerator / denominator), description);
    }

    public ProblemDtoRs getVariation(ProblemDtoRq problemDtoRq) {
        long m = factorialMethod(problemDtoRq.getM());
        int result = problemDtoRq.getM() - problemDtoRq.getN();
        long denominator = factorialMethod(result);
        long resultFinal = (m / denominator);
        String description = ConteoConstants.SOLUCION + ConteoConstants.VARIACION;
        return buildResult(resultFinal, description);
    }

    public ProblemDtoRs getVariationWithRepeat(ProblemDtoRq problemDtoRq) {
        Long m = Long.valueOf(problemDtoRq.getM());
        Long n = Long.valueOf(problemDtoRq.getN());
        double result = Math.pow(m, n);
        String description = ConteoConstants.SOLUCION + ConteoConstants.VARIACION_REPETICION;
        return buildResult(Long.valueOf(String.valueOf(result)), description);
    }

    public ProblemDtoRs getCombination(ProblemDtoRq problemDtoRq) {
        Long m = factorialMethod(problemDtoRq.getM());
        Long n = factorialMethod(problemDtoRq.getN());
        Long mMenosN = factorialMethod(problemDtoRq.getM() - problemDtoRq.getN());
        Long denominator = n * mMenosN;
        String description = ConteoConstants.SOLUCION + ConteoConstants.COMBINATORIA;
        return buildResult((m / denominator), description);
    }

    public ProblemDtoRs getCombinationWithRepeat(ProblemDtoRq problemDtoRq) {
        Long numerator =  factorialMethod((problemDtoRq.getM() + problemDtoRq.getN() - 1));
        Long n = factorialMethod(problemDtoRq.getN());
        Long mMenos1 = factorialMethod(problemDtoRq.getM() - 1);
        Long denominator = (n * mMenos1);
        String description = ConteoConstants.SOLUCION + ConteoConstants.COMBINATORIA_REPETICION;
        return buildResult((numerator / denominator), description);
    }
}
