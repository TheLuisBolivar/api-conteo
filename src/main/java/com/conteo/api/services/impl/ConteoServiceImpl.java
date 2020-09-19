package com.conteo.api.services.impl;

import com.conteo.api.models.dtos.ProblemDtoRq;
import com.conteo.api.models.dtos.ProblemDtoRs;
import com.conteo.api.services.service.ConteoService;
import com.conteo.api.utils.ConteoConstants;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.stream.LongStream;

@Service
public class ConteoServiceImpl implements ConteoService {

    private BigDecimal factorialMethod(int n) {
        Long result = LongStream.rangeClosed(1, n)
                .reduce(1, (long x, long y) -> x * y);
        return BigDecimal.valueOf(result);
    }

    private ProblemDtoRs buildResult(BigDecimal result, String description) {
        ProblemDtoRs problemDtoRs = ProblemDtoRs.builder()
                .resultado(result)
                .description(description)
                .build();

        return problemDtoRs;
    }

    private BigDecimal getListFactorialResult(List<Integer> listRepeats, Integer n) throws Exception {
        BigDecimal memory;
        BigDecimal y = new BigDecimal(1);
        BigDecimal result = new BigDecimal(0);

        Boolean correctList = validatelListToRepeat(listRepeats, n);
        if(correctList){
            for (Integer i : listRepeats) {
                memory = factorialMethod(i);
                result = y.multiply(memory);
                y = result;
            }
        }else{
            throw new Exception("La parametrización de la permutación con repetición es erronea.");
        }

        return result;
    }

    private Boolean validatelListToRepeat(List<Integer> integerList, Integer n){
        Integer value = 0;
        Boolean result = Boolean.TRUE;

        for(Integer index : integerList){
            value += index;
        }

        if(n < value){
            result = Boolean.FALSE;
        }

        return result;
    }

    public ProblemDtoRs getPermutation(ProblemDtoRq problemDtoRq) {
        BigDecimal result = factorialMethod(problemDtoRq.getN());
        String description = ConteoConstants.SOLUCION + ConteoConstants.PERMUTACION;
        return buildResult(result, description);
    }

    public ProblemDtoRs getPermutationCircular(ProblemDtoRq problemDtoRq) {
        BigDecimal result = factorialMethod(problemDtoRq.getN() - 1);
        String description = ConteoConstants.SOLUCION + ConteoConstants.PERMUTACION_CIRCULAR;
        return buildResult(result, description);
    }

    public ProblemDtoRs getPermutationWithRepeat(ProblemDtoRq problemDtoRq) throws Exception {
        BigDecimal numerator = factorialMethod(problemDtoRq.getN());
        BigDecimal denominator = getListFactorialResult(problemDtoRq.getListVariablesToRepeat(), problemDtoRq.getN());
        String description = ConteoConstants.SOLUCION + ConteoConstants.PERMUTACION_REPETICION;
        BigDecimal resultFinal = numerator.divide(denominator, MathContext.DECIMAL128);
        return buildResult(resultFinal, description);
    }

    public ProblemDtoRs getVariation(ProblemDtoRq problemDtoRq) {
        BigDecimal m = factorialMethod(problemDtoRq.getM());
        Integer result = problemDtoRq.getM() - problemDtoRq.getN();
        BigDecimal denominator = factorialMethod(result);
        BigDecimal resultFinal = m.divide(denominator, MathContext.DECIMAL128);
        String description = ConteoConstants.SOLUCION + ConteoConstants.VARIACION;
        return buildResult(resultFinal, description);
    }

    public ProblemDtoRs getVariationWithRepeat(ProblemDtoRq problemDtoRq) {
        Long m = Long.valueOf(problemDtoRq.getM());
        Long n = Long.valueOf(problemDtoRq.getN());
        BigDecimal result = BigDecimal.valueOf(Math.pow(m, n));
        String description = ConteoConstants.SOLUCION + ConteoConstants.VARIACION_REPETICION;
        return buildResult(result, description);
    }

    public ProblemDtoRs getCombination(ProblemDtoRq problemDtoRq) {
        BigDecimal m = factorialMethod(problemDtoRq.getM());
        BigDecimal n = factorialMethod(problemDtoRq.getN());
        BigDecimal mMenosN = factorialMethod(problemDtoRq.getM() - problemDtoRq.getN());
        BigDecimal denominator = n.multiply(mMenosN);
        String description = ConteoConstants.SOLUCION + ConteoConstants.COMBINATORIA;
        BigDecimal resultFinal = m.divide(denominator, MathContext.DECIMAL128);
        return buildResult(resultFinal, description);
    }

    public ProblemDtoRs getCombinationWithRepeat(ProblemDtoRq problemDtoRq) {
        BigDecimal numerator = factorialMethod((problemDtoRq.getM() + problemDtoRq.getN() - 1));
        BigDecimal n = factorialMethod(problemDtoRq.getN());
        BigDecimal mMenos1 = factorialMethod(problemDtoRq.getM() - 1);
        BigDecimal denominator = n.multiply(mMenos1);
        String description = ConteoConstants.SOLUCION + ConteoConstants.COMBINATORIA_REPETICION;
        BigDecimal resultFinal = numerator.divide(denominator, MathContext.DECIMAL128);
        return buildResult(resultFinal, description);
    }
}
