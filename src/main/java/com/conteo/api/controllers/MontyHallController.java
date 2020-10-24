package com.conteo.api.controllers;

import com.conteo.api.models.dtos.conteo.ProblemDtoRq;
import com.conteo.api.models.dtos.montyhall.MontyHallReqDto;
import com.conteo.api.models.dtos.montyhall.MontyHallResDto;
import com.conteo.api.services.montyhall.service.MontyHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monty-hall")
public class MontyHallController {

    @Autowired private MontyHallService montyHallService;

    @PostMapping("/play")
    public ResponseEntity calculate(@RequestBody MontyHallReqDto montyHallReqDto) throws Exception {
        MontyHallResDto montyHallResDto = montyHallService.calculate(montyHallReqDto);
        return new ResponseEntity(montyHallResDto, HttpStatus.OK);
    }
}
