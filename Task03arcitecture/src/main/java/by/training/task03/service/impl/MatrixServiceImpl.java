package by.training.task03.service.impl;

import by.training.task03.service.MatrixService;
import by.training.task03.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;


public class MatrixServiceImpl implements MatrixService {

    static final Logger serviceLogger = LogManager.getLogger("ServiceLog");

    public double[][] randomIni(int columns, int rows, int minValue, int maxValue) throws ServiceException {
        serviceLogger.info("User input: columns=" + columns + " rows=" + rows + " minValue=" + minValue + " maxValue=" + maxValue);
        if (columns < 1 || rows < 1) throw new ServiceException("columns and rows must jave at least 1 number");
        if (minValue > maxValue) throw new ServiceException("min value can't be more than max");

        double matrix[][] = new double[columns][rows];

        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                int value = (int) ((Math.random() * (maxValue - minValue)) + minValue);
                matrix[i][j] = value;
            }
        }

        System.out.println("ini matrix is " + Arrays.deepToString(matrix));
        return matrix;
    }

    public double[][] matrixCreator(String[] matrix, int columns, int rows) throws ServiceException {
        if (columns < 1 || rows < 1) {
            throw new ServiceException("matrix must have alt least 1 column and row");
        }

        if (columns * rows != matrix.length) {
            throw new ServiceException("the dimension of the matrix does not match");
        }

        double[][] matrix1 = new double[columns][rows];
        int k = 0;
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                matrix1[i][j] = Double.parseDouble(matrix[k]);
                k++;
            }
        }
        return matrix1;
    }

    public double[][] transposition(double[][] matrix) {
        int columns = matrix.length;
        int rows = matrix[0].length;
        double[][] result = new double[rows][columns];
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }

    public double[][] multiplication(double[][] matrix1, double[][] matrix2) throws ServiceException {
        int columns = matrix1.length;
        int rows = matrix2[0].length;
        if (rows == columns) {
            double[][] result = new double[columns][rows];
            for (int i = 0; i < columns; i++) {
                for (int j = 0; j < rows; j++) {
                    int value = 0;
                    for (int k = 0; k < matrix1[0].length; k++) {
                        value += matrix1[i][k] * matrix2[k][j];
                    }
                    result[i][j] = value;
                }
            }
            return result;
        } else {
            throw new ServiceException("those 2 matrix can't be multiplied, clear matrix and try with another one");
        }
    }

    public double[][] multiplicationOnNum(double[][] matrix, int num) {
        int columns = matrix.length;
        int rows = matrix[0].length;
        double[][] result = new double[columns][rows];
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                result[i][j] = matrix[i][j] * num;
            }
        }
        return result;
    }

    public double[][] sumMatrix(double[][] matrix1, double[][] matrix2) throws ServiceException {
        int columns = matrix1.length;
        int rows = matrix1[0].length;
        if (columns == matrix2.length && rows == matrix2[0].length) {
            double[][] result = new double[columns][rows];
            for (int i = 0; i < columns; i++) {
                for (int j = 0; j < rows; j++) {
                    result[i][j] = matrix1[i][j] + matrix2[i][j];
                }
            }
            return result;
        } else {
            throw new ServiceException("those 2 matrix can't be summed, clear matrix and try with another one");
        }

    }

    public double[][] matrixSubtraction(double[][] matrix1, double[][] matrix2) throws ServiceException {
        int columns = matrix1.length;
        int rows = matrix1[0].length;
        if (columns == matrix2.length && rows == matrix2[0].length) {
            double[][] result = new double[columns][rows];
            for (int i = 0; i < columns; i++) {
                for (int j = 0; j < rows; j++) {
                    result[i][j] = matrix1[i][j] - matrix2[i][j];
                }
            }
            return result;
        } else {
            throw new ServiceException("those 2 matrix can't be subtracted, clear matrix and try with another one");
        }
    }
}
