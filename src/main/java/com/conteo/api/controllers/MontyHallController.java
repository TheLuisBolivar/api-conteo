package com.conteo.api.controllers;

import com.conteo.api.models.dtos.ProblemDtoRq;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monty-hall")
public class MontyHallController {

    @PostMapping("/solve")
    public ResponseEntity getResultConteo(@RequestBody ProblemDtoRq problemDtoRq) throws Exception {
        return new ResponseEntity("Solucion", HttpStatus.OK);
    }
}
