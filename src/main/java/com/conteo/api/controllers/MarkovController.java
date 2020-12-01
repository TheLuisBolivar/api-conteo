package com.conteo.api.controllers;
import com.conteo.api.models.dtos.markov.MarkovReqDto;
import com.conteo.api.models.dtos.markov.MarkovResDto;
import com.conteo.api.services.markov.service.MarkovService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/markov")
public class MarkovController {

    @Autowired
    private MarkovService markovService;

    @PostMapping("/calculate")
    public ResponseEntity calculate(@RequestBody MarkovReqDto markovReqDto) throws Exception {
        MarkovResDto markovResDto = markovService.calculate(markovReqDto);
        return new ResponseEntity(markovResDto, HttpStatus.OK);
    }
}
