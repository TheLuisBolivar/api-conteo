package com.conteo.api.services.markov.impl;

import com.conteo.api.models.dtos.markov.MarkovReqDto;
import com.conteo.api.models.dtos.markov.MarkovResDto;
import com.conteo.api.services.markov.service.MarkovService;
import com.conteo.api.utils.markov.MarkovConstants;
import com.conteo.api.utils.markov.MarkovUtils;
import com.conteo.api.utils.utils.ParseUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class MarkovServiceImpl implements MarkovService {

    @Autowired private MarkovUtils markovUtils;

    public MarkovResDto calculate(MarkovReqDto markovReqDto) throws Exception {
        log.info("[calculate] MarkovReqDto: {}", ParseUtil.objectLog(markovReqDto));
        return getResult(markovReqDto);
    }

    private MarkovResDto getResult(MarkovReqDto markovReqDto) throws Exception {
        validateMarkovReqDto(markovReqDto);
        double[][] matrixN = markovUtils.generateExponentialMatrix(markovReqDto.getMatrixMarkov(), markovReqDto.getN());
        double[][] result = markovUtils.multiplyMatrices(markovReqDto.getInitialState(), matrixN);
        return buildResult(Boolean.TRUE, MarkovConstants.SUCCESSFUL_RESULT, matrixN, result);
    }

    private MarkovResDto buildResult(Boolean status, String description, double[][] resultMatrix, double[][] resultState){
        MarkovResDto markovResDto = MarkovResDto.builder()
                .status(status)
                .description(description)
                .resultMatrix(resultMatrix)
                .resultState(resultState).build();

        log.info("[buildResult]: MarkovResDto: {}", ParseUtil.objectLog(markovResDto));
        return markovResDto;
    }

    private void validateMarkovReqDto(MarkovReqDto markovReqDto) throws Exception {
        validateN(markovReqDto);
        validateRequest(markovReqDto);
        validateMatrixMarkov(markovReqDto);
        validateInitialState(markovReqDto);
        validateRows(markovReqDto.getMatrixMarkov());
        validateRows(markovReqDto.getInitialState());
        validateMatrixMarkovAndInitialState(markovReqDto.getMatrixMarkov(), markovReqDto.getInitialState());
    }

    private void validateRows(double [][] value) throws Exception {
        for(int i = 0; i < value.length; i++){
            double result = 0;
            for(int j = 0; j < value[i].length; j++){
                result += value[i][j];
            }

            if(result != 1){
                log.error("[validateRows]: the sum is different that 1, please validate data");
                throw new Exception("The sum is different that 1, please validate data");
            }
        }

    }

    private void validateMatrixMarkov(MarkovReqDto markovReqDto) throws Exception {
        markovUtils.validateDataOnMatrix(markovReqDto.getMatrixMarkov());
        if (markovReqDto.getMatrixMarkov().length == 0) {
            log.error("[validateMatrixMarkov]: The list its empty, validate data.");
            throw new Exception("The list its empty, validate data.");
        } else {
            log.info("[validateMatrixMarkov]: ready to transform!");
        }
    }

    private void validateInitialState(MarkovReqDto markovReqDto) throws Exception {
        markovUtils.validateDataOnMatrix(markovReqDto.getInitialState());
        if (markovReqDto.getInitialState().length == 0) {
            log.error("[validateInitialState]: The list its empty, validate data.");
            throw new Exception("The list its empty, validate data.");
        } else {
            log.info("[validateInitialState]: ready to transform!");
        }
    }

    private void validateRequest(MarkovReqDto markovReqDto) throws Exception {
        if (markovReqDto == null) {
            log.error("[validateRequest]: Error in request, send information");
            throw new Exception("Error in request, send information.");
        }
    }

    private void validateMatrixMarkovAndInitialState(double[][] matrix, double[][] initialState) throws Exception {
        int rowsMatrix = matrix.length;
        int columnsMatrix = matrix[0].length;
        int rowsInitialState = initialState.length;
        int columnsInitialState = initialState[0].length;

        log.info("[validateMatrixMarkovAndInitialState]: initialState: ({}, {}), matrix: ({}, {})",
                rowsInitialState, columnsInitialState, rowsMatrix, columnsMatrix);

        if(columnsInitialState != rowsMatrix){
            log.error("[validateMatrixMarkovAndInitialState]: The sizes matrix are not compatible, please in matrix's correct");
            throw new Exception("The sizes matrix are not compatible, please in matrix's correct");
        }

        log.info("[validateMatrixMarkovAndInitialState]: the result size matrix is: ({}, {})",
                rowsInitialState, columnsMatrix);
    }

    private void validateN(MarkovReqDto markovReqDto) throws Exception {
        if(markovReqDto.getN() == null){
            log.error("[validateN]: n value is null");
            throw new Exception("n value is null");
        }

        if(markovReqDto.getN() <= 0){
            log.error("[validateN]: Value of n is incorrect, please validate: {}", markovReqDto.getN());
            throw new Exception("n value is incorrect, please validate");
        }
    }
}
