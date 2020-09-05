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

    private Double factorialMethod(int n){
        Long tutu = LongStream.rangeClosed(1, n)
                .reduce(1, (long x, long y) -> x * y);
        return Double.parseDouble(String.valueOf(tutu));
    }

    private ProblemDtoRs buildResult(Double result, String description){
        ProblemDtoRs problemDtoRs = ProblemDtoRs.builder()
                .resultado(result)
                .description(description)
                .build();

        return problemDtoRs;
    }

    private Double getListFactorialResult(List<Integer> listRepeats){
        Double result = 1D;
        for(Integer i : listRepeats){
            result *= factorialMethod(i);
        }
        return result;
    }

    public ProblemDtoRs getPermutation(ProblemDtoRq problemDtoRq) {
        Double result = factorialMethod(problemDtoRq.getN());
        String description = ConteoConstants.SOLUCION +ConteoConstants.PERMUTACION;
        return buildResult(result, description);
    }

    public ProblemDtoRs getPermutationCircular(ProblemDtoRq problemDtoRq) {
        Double result = factorialMethod(problemDtoRq.getN() - 1);
        String description = ConteoConstants.SOLUCION +ConteoConstants.PERMUTACION_CIRCULAR;
        return buildResult(result, description);
    }

    public ProblemDtoRs getPermutationWithRepeat(ProblemDtoRq problemDtoRq) {
        Double numerator = factorialMethod(problemDtoRq.getN());
        Double denominator = getListFactorialResult(problemDtoRq.getListVariablesToRepeat());
        String description = ConteoConstants.SOLUCION + ConteoConstants.PERMUTACION_REPETICION;
        Double resultFinal = numerator / denominator;
        return buildResult(resultFinal, description);
    }

    public ProblemDtoRs getVariation(ProblemDtoRq problemDtoRq) {
        Double m = factorialMethod(problemDtoRq.getM());
        Integer result = problemDtoRq.getM() - problemDtoRq.getN();
        Double denominator = factorialMethod(result);
        Double resultFinal = (m / denominator);
        String description = ConteoConstants.SOLUCION + ConteoConstants.VARIACION;
        return buildResult(resultFinal, description);
    }

    public ProblemDtoRs getVariationWithRepeat(ProblemDtoRq problemDtoRq) {
        Long m = Long.valueOf(problemDtoRq.getM());
        Long n = Long.valueOf(problemDtoRq.getN());
        Double result = Math.pow(m, n);
        String description = ConteoConstants.SOLUCION + ConteoConstants.VARIACION_REPETICION;
        return buildResult(result, description);
    }

    public ProblemDtoRs getCombination(ProblemDtoRq problemDtoRq) {
        Double m = factorialMethod(problemDtoRq.getM());
        Double n = factorialMethod(problemDtoRq.getN());
        Double mMenosN = factorialMethod(problemDtoRq.getM() - problemDtoRq.getN());
        Double denominator = n * mMenosN;
        String description = ConteoConstants.SOLUCION + ConteoConstants.COMBINATORIA;
        Double resultFinal = m / denominator;
        return buildResult(resultFinal, description);
    }

    public ProblemDtoRs getCombinationWithRepeat(ProblemDtoRq problemDtoRq) {
        Double numerator =  factorialMethod((problemDtoRq.getM() + problemDtoRq.getN() - 1));
        Double n = factorialMethod(problemDtoRq.getN());
        Double mMenos1 = factorialMethod(problemDtoRq.getM() - 1);
        Double denominator = (n * mMenos1);
        String description = ConteoConstants.SOLUCION + ConteoConstants.COMBINATORIA_REPETICION;
        Double resultFinal = numerator / denominator;
        return buildResult(resultFinal, description);
    }
}
