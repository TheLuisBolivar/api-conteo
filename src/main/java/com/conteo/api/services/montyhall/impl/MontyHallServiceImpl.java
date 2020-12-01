package com.conteo.api.services.montyhall.impl;

import com.conteo.api.models.dtos.montyhall.MontyHallReqDto;
import com.conteo.api.models.dtos.montyhall.MontyHallResDto;
import com.conteo.api.models.entities.MontyHall;
import com.conteo.api.models.repositories.MontyHallRepo;
import com.conteo.api.services.montyhall.service.MontyHallService;
import com.conteo.api.utils.montyhall.MontyHallConstant;
import com.conteo.api.utils.montyhall.MontyHallUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class MontyHallServiceImpl implements MontyHallService {

    @Autowired
    private MontyHallUtil montyHallUtil;

    @Autowired
    private MontyHallRepo montyHallRepo;

    public MontyHallResDto play(MontyHallReqDto montyHallReqDto) throws Exception {
        Optional<MontyHall> optional = getMontyHallByTransactionId(montyHallReqDto.getTransactionId());
        return validateGame(montyHallReqDto, optional);
    }

    private MontyHallResDto validateGame(MontyHallReqDto montyHallReqDto, Optional<MontyHall> optional) throws Exception {
        MontyHallResDto montyHallResDto;
        Boolean status;
        if (optional.isPresent()) {
            montyHallUtil.validateEndGame(montyHallReqDto.getOptions());
            status = validateResponse(montyHallReqDto, optional.get());
            montyHallResDto = buildFinishGame(status, montyHallReqDto, optional.get());
        } else {
            montyHallUtil.validateStartGame(montyHallReqDto.getOptions());
            Integer responseUser = montyHallUtil.getResponseUser(montyHallReqDto.getOptions());
            Integer correctDoor = montyHallUtil.generateCorrectDoor(MontyHallConstant.AMOUNT_STANDARD_DOORS);
            Integer suggestion = montyHallUtil.getSuggestion(responseUser, MontyHallConstant.AMOUNT_STANDARD_DOORS, correctDoor);
            status = saveGame(montyHallReqDto.getTransactionId(), responseUser, suggestion, correctDoor);
            log.info("[calculate]: status save game: {}", status);
            montyHallResDto = buildResponseSaveGame(
                    responseUser, suggestion, montyHallReqDto.getTransactionId(), status);
        }

        return montyHallResDto;
    }

    private MontyHallResDto buildFinishGame(Boolean result, MontyHallReqDto montyHallReqDto, MontyHall montyHall) {
        String finalResult = result ?
                MontyHallConstant.WINNER :
                MontyHallConstant.LOSER;
        Calendar calendar = Calendar.getInstance();
        changesValueToCar(montyHall, montyHallReqDto);
        montyHall.setDateFinish(String.valueOf(calendar.getTimeInMillis()));
        montyHall.setFinished(MontyHallConstant.GAME_FINISHED);
        montyHall.setResult(finalResult);

        Boolean save = saveMontyHall(montyHall);

        return MontyHallResDto.builder()
                .options(montyHallReqDto.getOptions())
                .gameSaved(save)
                .transactionId(montyHallReqDto.getTransactionId())
                .result(finalResult).build();
    }

    private void changesValueToCar(MontyHall montyHall, MontyHallReqDto montyHallReqDto) {
        List<String> options = montyHallReqDto.getOptions();
        for (int i = 0; i < options.size(); i++) {
            if ((i + 1) == montyHall.getDoorCorrect()) {
                options.set(i, MontyHallConstant.CAR);
            }
        }
    }

    private Boolean validateResponse(MontyHallReqDto montyHallReqDto, MontyHall montyHall) throws Exception {
        MontyHall montyHallBuilder = montyHallUtil.transformDataToMontyHall(montyHallReqDto.getOptions());
        return montyHallUtil.resultGame(montyHallBuilder, montyHall);
    }

    private Optional<MontyHall> getMontyHallByTransactionId(Long transactionId) {
        Optional<MontyHall> optional = Optional.empty();
        try {
            MontyHall montyHall = montyHallRepo.findByTransactionId(transactionId);
            optional = montyHallUtil.validateMontyHall(montyHall);
            log.info("[getMontyHallByTransactionId]: get MontyHall successful.");
        } catch (Exception e) {
            log.info("[getMontyHallByTransactionId]: error trying get montyHall object: ", e);
        }
        return optional;
    }

    private Boolean saveGame(Long transactionId, Integer responseUser, Integer suggestion, Integer correctDoor) {
        Calendar calendar = Calendar.getInstance();
        MontyHall montyHall = new MontyHall();
        montyHall.setDateStart(String.valueOf(calendar.getTimeInMillis()));
        montyHall.setDoorCorrect(correctDoor);
        montyHall.setDoorSelected(responseUser);
        montyHall.setDoorSuggestion(suggestion);
        montyHall.setTransactionId(transactionId);
        montyHall.setFinished(MontyHallConstant.GAME_NOT_FINISHED);
        montyHall.setDateFinish("0");
        montyHall.setResult(MontyHallConstant.IN_PROGRESS);
        return saveMontyHall(montyHall);
    }

    private MontyHallResDto buildResponseSaveGame(Integer responseUser, Integer suggestion,
                                                  Long transactionId, Boolean statusGame) {
        List<String> listOptions = montyHallUtil.buildListOptions(
                responseUser, suggestion, MontyHallConstant.AMOUNT_STANDARD_DOORS);
        return MontyHallResDto.builder()
                .options(listOptions)
                .transactionId(transactionId)
                .gameSaved(statusGame)
                .result(MontyHallConstant.IN_PROGRESS)
                .build();
    }

    private Boolean saveMontyHall(MontyHall montyHall) {
        Boolean result = Boolean.FALSE;
        try {
            montyHallRepo.save(montyHall);
            result = Boolean.TRUE;
            log.info("[saveMontyHall]: saved successfully");
        } catch (Exception e) {
            log.error("[saveMontyHall]: Error trying save montyHall", e);
        }

        return result;
    }
}
