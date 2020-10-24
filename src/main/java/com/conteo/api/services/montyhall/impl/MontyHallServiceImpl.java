package com.conteo.api.services.montyhall.impl;

import com.conteo.api.models.dtos.montyhall.MontyHallReqDto;
import com.conteo.api.models.dtos.montyhall.MontyHallResDto;
import com.conteo.api.services.montyhall.service.MontyHallService;
import com.conteo.api.utils.montyhall.MontyHallConstant;
import com.conteo.api.utils.montyhall.MontyHallUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class MontyHallServiceImpl implements MontyHallService {

    @Autowired private MontyHallUtil montyHallUtil;

    public MontyHallResDto calculate(MontyHallReqDto montyHallReqDto) throws Exception {
        Integer door = montyHallUtil.generateCorrectDoor(MontyHallConstant.AMOUNT_STANDARD_DOORS);
        Integer responseUser = montyHallUtil.getResponseUser(montyHallReqDto.getOptions());
        validateResponseUser(responseUser);
        return null;
    }

    private void validateResponseUser(Integer responseUser) throws Exception {
        if(responseUser.equals(0)){
            log.error("[validateResponseUser]: not has door selected.");
            throw new Exception("have not input one answer, we'll doing together, please select one door with a one number one");
        }
    }
}
