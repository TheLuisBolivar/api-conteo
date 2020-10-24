package com.conteo.api.services.bayes.impl;

import com.conteo.api.models.dtos.bayes.BayesReqDto;
import com.conteo.api.models.dtos.bayes.BayesResDto;
import com.conteo.api.services.bayes.service.BayesService;
import com.conteo.api.utils.bayes.BayesUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

@Log4j2
@Service
public class BayesServiceImpl implements BayesService {

    @Autowired private BayesUtil bayesUtil;

    public BayesResDto calculate(BayesReqDto bayesReqDto) throws Exception {
        BigDecimal numerator = multiplicationValues(bayesReqDto.getBIntersectionAi());
        BigDecimal denominator = multiplicationOfList(bayesReqDto.getValuesProbabilityB());

        if (numerator.equals(BigDecimal.ZERO) || denominator.equals(BigDecimal.ZERO)) {
            log.error("[calculate]: Error doing operations, validate");
            throw new Exception("[calculate]: Error doing operations, validate");
        }
        BigDecimal result = numerator.divide(denominator, MathContext.DECIMAL128);
        return BayesResDto.builder()
                .result(result)
                .description("Operation Successful.")
                .build();
    }

    private BigDecimal multiplicationValues(List<BigDecimal> bigDecimalList) throws Exception {
        BigDecimal result = BigDecimal.ZERO;

        if (!bigDecimalList.isEmpty()) {
            log.info("[multiplicationValues]: list are values: {}", bigDecimalList.size());
            bayesUtil.validateListData(bigDecimalList);
            result = bigDecimalList.stream()
                    .reduce(BigDecimal.ONE, (a, b) -> b.multiply(a));
        }

        return result;
    }

    private BigDecimal multiplicationOfList(List<List<BigDecimal>> branchList) throws Exception {
        BigDecimal result = BigDecimal.ZERO;

        if (!branchList.isEmpty()) {
            log.info("[multiplicationValues]: list are values");
            for (List<BigDecimal> index : branchList) {
                BigDecimal temporally = multiplicationValues(index);
                result =result.add(temporally);
            }
        }

        return result;
    }
}
