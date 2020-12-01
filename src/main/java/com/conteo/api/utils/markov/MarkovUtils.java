package com.conteo.api.utils.markov;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class MarkovUtils {

    public double[][] generateExponentialMatrix(double[][] matrix, int iterations) {
        double[][] matrixOriginal = matrix;
        for(int i = 0; i < iterations - 1; i++){
            matrix = multiplyMatrices(matrix, matrixOriginal);
        }
        return matrix;
    }

    public double[][] multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix) {
        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = multiplyMatricesCell(firstMatrix, secondMatrix, row, col);
            }
        }

        return result;
    }

    double multiplyMatricesCell(double[][] firstMatrix, double[][] secondMatrix, int row, int col) {
        double cell = 0;
        for (int i = 0; i < secondMatrix.length; i++) {
            cell += firstMatrix[row][i] * secondMatrix[i][col];
        }
        return cell;
    }

    public void validateDataOnMatrix(double[][] matrix) throws Exception {
        for(int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length; j++){
                double value = matrix[i][j];
                if(value > 1 || value < 0){
                    log.info("[validateDataOnMatrix]: Error validating data, please input data correct: {}", matrix[i][j]);
                    throw new Exception("Error validating data, please input data correct");
                }
            }
        }
    }
}
