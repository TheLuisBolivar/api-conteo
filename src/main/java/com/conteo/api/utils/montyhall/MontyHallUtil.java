package com.conteo.api.utils.montyhall;

import com.conteo.api.models.entities.MontyHall;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
public class MontyHallUtil {

    public Integer generateCorrectDoor(Integer limit) {
        return generateRandom(limit - 1) + 1;
    }

    private Integer generateRandom(Integer limit) {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(limit);
    }

    public Integer getResponseUser(List<String> stringList) {
        Integer result = -1;
        for (int i = 0; i < stringList.size(); i++) {
            String value = stringList.get(i);

            if (MontyHallConstant.FLAG_USER.equals(value)) {
                result = i;
            }
        }

        return result + 1;
    }

    public Integer getSuggestion(Integer responseUser, Integer amountDoors, Integer correctDoor) {
        List<Integer> listSuggestion = new ArrayList<>();
        for (int i = 0; i < amountDoors; i++) {
            if ((i + 1 != responseUser) && (i + 1 != correctDoor)) {
                listSuggestion.add(i + 1);
            }
        }

        Integer addressList = generateRandom(listSuggestion.size());
        return listSuggestion.get(addressList);
    }

    public List<String> buildListOptions(Integer responseUse, Integer suggestion, Integer amountDoors) {
        List<String> listOptions = new ArrayList<>();
        for (int i = 0; i < amountDoors; i++) {
            if (i + 1 == responseUse) {
                listOptions.add(MontyHallConstant.FLAG_USER);
            } else if (i + 1 == suggestion) {
                listOptions.add(MontyHallConstant.SHEEP);
            } else {
                listOptions.add(MontyHallConstant.DOOR);
            }
        }
        return listOptions;
    }

    public void validateResponseUser(Integer responseUser) throws Exception {
        if (responseUser.equals(0)) {
            log.error("[validateResponseUser]: not has door selected.");
            throw new Exception("have not input one answer, we'll doing together, please select one door with a one number one");
        }
    }
}
