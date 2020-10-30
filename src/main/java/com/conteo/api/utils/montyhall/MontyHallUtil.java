package com.conteo.api.utils.montyhall;

import com.conteo.api.models.entities.MontyHall;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Integer getResponseUser(List<String> stringList) throws Exception {
        Integer result = -1;
        for (int i = 0; i < stringList.size(); i++) {
            String value = stringList.get(i);

            if (MontyHallConstant.DOOR_SELECTED.equals(value)) {
                result = i;
            }
        }
        result += 1;
        validateResponseUser(result);

        return result;
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
                listOptions.add(MontyHallConstant.DOOR_SELECTED);
            } else if (i + 1 == suggestion) {
                listOptions.add(MontyHallConstant.GOAT);
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

    public MontyHall transformDataToMontyHall(List<String> listOptions) throws Exception {
        Integer resultUser = 0;
        Integer suggestion = 0;
        Integer flagResultUser = 0;
        Integer flagSuggestion = 0;
        for (int i = 0; i < listOptions.size(); i++) {
            if (listOptions.get(i).equals(MontyHallConstant.DOOR_SELECTED)) {
                flagResultUser++;
                resultUser = i + 1;
            } else if (listOptions.get(i).equals(MontyHallConstant.GOAT)) {
                flagSuggestion++;
                suggestion = i + 1;
            }
        }

        validateTransformData(flagResultUser, flagSuggestion);
        return buildTemporalMontyHall(suggestion, resultUser);
    }

    private void validateTransformData(Integer flagResultUser, Integer flagSuggestion) throws Exception {
        if (flagResultUser > 1 || flagSuggestion > 1) {
            log.error("[validateTransformData]: Something its strangest in this game.");
            throw new Exception("Something its strangest in this game.");
        }
    }

    private MontyHall buildTemporalMontyHall(Integer suggestion, Integer resultUser) {
        MontyHall montyHall = new MontyHall();
        montyHall.setDoorSuggestion(suggestion);
        montyHall.setDoorSelected(resultUser);
        return montyHall;
    }

    public Boolean resultGame(MontyHall montyHallBuilder, MontyHall montyHall) {
        validateDoorChanges(montyHallBuilder, montyHall);
        return montyHall.getDoorCorrect().equals(montyHallBuilder.getDoorSelected());
    }

    private void validateDoorChanges(MontyHall montyHallBuilder, MontyHall montyHall) {
        if (montyHallBuilder.getDoorSelected().equals(montyHall.getDoorSelected())) {
            log.info("[resultGame]: it conserve same door: {}", montyHallBuilder.getDoorSelected());
        } else {
            log.info("[resultGame]: changes door, first door selected: {}, door selected finally: {}"
                    , montyHall.getDoorSelected()
                    , montyHallBuilder.getDoorSelected());
        }
    }

    public Optional<MontyHall> validateMontyHall(MontyHall montyHall) {
        Optional<MontyHall> optional = Optional.empty();
        if (Boolean.FALSE.equals(montyHall.getFinished()) ||
                MontyHallConstant.IN_PROGRESS.equals(montyHall.getResult())) {
            optional = Optional.of(montyHall);
        }

        return optional;
    }

    public void validateStartGame(List<String> options) throws Exception {
        Boolean countDoors = countDoors(options);
        Boolean countGoats = countGoat(options);
        Boolean countDoorSelected = countDoorsSelected(options);

        log.info("[validateStartGame]: countDoors: {}, countGoats {}", countDoors, countGoats);
        if (Boolean.TRUE.equals(countDoors) ||
                Boolean.TRUE.equals(countGoats) ||
                Boolean.TRUE.equals(countDoorSelected)) {
            throw new Exception("Los datos se ven sospechosos amigo.");
        }
    }

    private Boolean countDoors(List<String> options) {
        Boolean result = Boolean.FALSE;
        int countDoors = 0;
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).equals(MontyHallConstant.DOOR)) {
                countDoors++;
            }
        }

        if (countDoors != 2) {
            result = Boolean.TRUE;
        }

        return result;
    }

    private Boolean countGoat(List<String> options) {
        Boolean result = Boolean.FALSE;
        int countGoats = 0;
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).equals(MontyHallConstant.GOAT)) {
                countGoats++;
            }
        }

        if (countGoats > 0) {
            result = Boolean.TRUE;
        }

        return result;
    }

    private Boolean countDoorsSelected(List<String> options) {
        Boolean result = Boolean.FALSE;
        int countDoorsSelected = 0;
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).equals(MontyHallConstant.DOOR_SELECTED)) {
                countDoorsSelected++;
            }
        }

        if (countDoorsSelected > 1) {
            result = Boolean.TRUE;
        }

        return result;
    }

    public void validateEndGame(List<String> options) throws Exception {
        Boolean countGoatEnd = countGoatEnd(options);
        Boolean countDoorActiveEnd = countDoorsActive(options);
        Boolean countDoorsSelected = countDoorsSelectedEnd(options);

        log.warn("[validateEndGame]: values: countGoatEnd: {}, countDoorActiveEnd: {}, countDoorsSelected: {}",
                countGoatEnd, countDoorActiveEnd, countDoorsSelected);
        if (Boolean.TRUE.equals(countGoatEnd) ||
                Boolean.TRUE.equals(countDoorActiveEnd) ||
                Boolean.TRUE.equals(countDoorsSelected)) {

            throw new Exception("Los datos se ven sospechosos amigo.");
        }
    }

    private Boolean countGoatEnd(List<String> options) {
        Boolean result = Boolean.FALSE;
        int countGoatEnd = 0;
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).equals(MontyHallConstant.GOAT)) {
                countGoatEnd++;
            }
        }

        if (countGoatEnd > 1) {
            result = Boolean.TRUE;
        }

        return result;
    }

    private Boolean countDoorsActive(List<String> options) {
        Boolean result = Boolean.FALSE;
        int countDoorsActive = 0;
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).equals(MontyHallConstant.DOOR)) {
                countDoorsActive++;
            }
        }

        if (countDoorsActive > 1) {
            result = Boolean.TRUE;
        }

        return result;
    }

    private Boolean countDoorsSelectedEnd(List<String> options) {
        Boolean result = Boolean.FALSE;
        int countDoorsSelected = 0;
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).equals(MontyHallConstant.DOOR_SELECTED)) {
                countDoorsSelected++;
            }
        }

        if (countDoorsSelected > 1) {
            result = Boolean.TRUE;
        }

        return result;
    }
}
