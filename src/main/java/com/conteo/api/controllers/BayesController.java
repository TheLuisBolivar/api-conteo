package com.conteo.api.controllers;

import com.conteo.api.models.dtos.bayes.BayesReqDto;
import com.conteo.api.models.dtos.bayes.BayesResDto;
import com.conteo.api.services.bayes.service.BayesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bayes")
public class BayesController {

    @Autowired private BayesService bayesService;

    @PostMapping("/calculate")
    public ResponseEntity calculate(@RequestBody BayesReqDto bayesReqDto) throws Exception {
        BayesResDto bayesResDto = bayesService.calculate(bayesReqDto);
        return new ResponseEntity(bayesResDto, HttpStatus.OK);
    }
}
