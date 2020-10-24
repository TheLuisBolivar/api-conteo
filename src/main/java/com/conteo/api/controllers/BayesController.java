package com.conteo.api.controllers;

import com.conteo.api.models.dtos.ProblemDtoRq;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bayes")
public class BayesController {

    @PostMapping("/solve")
    public ResponseEntity getResultMontyHall(@RequestBody ProblemDtoRq problemDtoRq) throws Exception {
        return new ResponseEntity("solucion", HttpStatus.OK);
    }
}
