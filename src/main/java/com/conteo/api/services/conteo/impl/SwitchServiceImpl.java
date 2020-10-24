package com.conteo.api.services.conteo.impl;

import com.conteo.api.models.dtos.ProblemDtoRq;
import com.conteo.api.models.dtos.ProblemDtoRs;
import com.conteo.api.services.conteo.service.ConteoService;
import com.conteo.api.services.conteo.service.SwitchService;
import com.conteo.api.utils.ConteoConstants;
import com.conteo.api.utils.ConteoUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Log4j2
@Service
public class SwitchServiceImpl implements SwitchService {

    @Autowired
    private ConteoService conteoService;

    @Autowired
    private ConteoUtils conteoUtils;

    public ProblemDtoRs resolveCase(ProblemDtoRq problemDtoRq) throws Exception {
        String caseConteo = conteoUtils.getCase(problemDtoRq);
        ProblemDtoRs problemDtoRs;
        switch (caseConteo) {
            case ConteoConstants.PERMUTACION:
                problemDtoRs = conteoService.getPermutation(problemDtoRq);
                break;
            case ConteoConstants.PERMUTACION_CIRCULAR:
                problemDtoRs = conteoService.getPermutationCircular(problemDtoRq);
                break;
            case ConteoConstants.PERMUTACION_REPETICION:
                problemDtoRs = conteoService.getPermutationWithRepeat(problemDtoRq);
                break;
            case ConteoConstants.VARIACION:
                problemDtoRs = conteoService.getVariation(problemDtoRq);
                break;
            case ConteoConstants.VARIACION_REPETICION:
                problemDtoRs = conteoService.getVariationWithRepeat(problemDtoRq);
                break;
            case ConteoConstants.COMBINATORIA:
                problemDtoRs = conteoService.getCombination(problemDtoRq);
                break;
            case ConteoConstants.COMBINATORIA_REPETICION:
                problemDtoRs = conteoService.getCombinationWithRepeat(problemDtoRq);
                break;
            default:
                problemDtoRs = ProblemDtoRs.builder()
                        .resultado(new BigDecimal(0))
                        .description(ConteoConstants.SIN_CASO).build();
                break;
        }
        return problemDtoRs;
    }

}
