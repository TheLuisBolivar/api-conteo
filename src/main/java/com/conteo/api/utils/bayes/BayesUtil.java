package com.conteo.api.utils.bayes;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Component
public class BayesUtil {

    private Boolean validateProbabilityGreater1(BigDecimal bigDecimal) {
        return bigDecimal.compareTo(BigDecimal.ONE) == 1 ?
                Boolean.FALSE :
                Boolean.TRUE;
    }

    private Boolean validateProbabilityLesser0(BigDecimal bigDecimal) {
        return ((bigDecimal.compareTo(BigDecimal.ZERO) != 1) &&
                (bigDecimal.compareTo(BigDecimal.ZERO) != 0)) ?
                Boolean.FALSE :
                Boolean.TRUE;
    }

    public Boolean validateProbability(BigDecimal bigDecimal) {
        Boolean result = Boolean.TRUE;
        Boolean validateLesser0 = validateProbabilityLesser0(bigDecimal);
        Boolean validateGreater1 = validateProbabilityGreater1(bigDecimal);

        if (Boolean.FALSE.equals(validateGreater1) ||
                Boolean.FALSE.equals(validateLesser0)) {
            result = Boolean.FALSE;
        }
        return result;
    }


    public void validateListData(List<BigDecimal> bigDecimalList) throws Exception {
        Set<BigDecimal> bigDecimalSet =
                bigDecimalList.stream()
                        .filter(m -> validateProbability(m).equals(Boolean.FALSE))
                        .collect(Collectors.toSet());

        if (!bigDecimalSet.isEmpty()) {
            log.error("[validateListData]: error, data it's not probability.");
            throw new Exception("[validateListData]: error, data it's not probability");
        }
    }

}
