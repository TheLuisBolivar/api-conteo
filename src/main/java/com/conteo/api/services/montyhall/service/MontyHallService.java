package com.conteo.api.services.montyhall.service;

import com.conteo.api.models.dtos.montyhall.MontyHallReqDto;
import com.conteo.api.models.dtos.montyhall.MontyHallResDto;

public interface MontyHallService {
    MontyHallResDto calculate(MontyHallReqDto montyHallReqDto) throws Exception;
}
