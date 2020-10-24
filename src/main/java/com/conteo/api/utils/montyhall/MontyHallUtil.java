package com.conteo.api.utils.montyhall;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.List;

@Log4j2
@Component
public class MontyHallUtil {

    public Integer generateCorrectDoor(Integer limit) {
        limit -= 1;
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(limit) + 1;
    }

    public Integer getResponseUser(List<String> stringList){
        Integer result = -1;
        for(int i = 0; i< stringList.size(); i++){
            String value = stringList.get(i);

            if(MontyHallConstant.FLAG_USER.equals(value)){
                result = i;
            }
        }

        return result + 1;
    }
}
