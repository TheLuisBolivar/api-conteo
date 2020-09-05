package com.conteo.api.services.impl;

import com.conteo.api.models.dtos.ProblemDtoRq;
import com.conteo.api.models.dtos.ProblemDtoRs;
import com.conteo.api.services.ConteoService;
import com.conteo.api.services.service.SwitchService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

@Log4j2
public class SwitchServiceImpl implements SwitchService {

    @Autowired private ConteoService conteoService;

    public ProblemDtoRs resolveCase(Object o) {
        return null;
    }

    private String getCase(ProblemDtoRq problemDtoRq){

        log.info("[getCase]: ProblemDtoRq: {}", problemDtoRq);

        if(Boolean.TRUE.equals(problemDtoRq.getEntranTodos())){

        }

        return null;
    }
}
