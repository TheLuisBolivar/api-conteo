package com.conteo.api.controllers;

import com.conteo.api.models.dtos.ProblemDtoRq;
import com.conteo.api.models.dtos.ProblemDtoRs;
import com.conteo.api.services.service.SwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class ApiConteoController {

    @Autowired private SwitchService switchService;

    @PostMapping()
    public ResponseEntity getResultConteo(@RequestParam ProblemDtoRq problemDtoRq){
        ProblemDtoRs problemDtoRs =  switchService.resolveCase(problemDtoRq);
        return new ResponseEntity(problemDtoRs, HttpStatus.OK);
    }
}
