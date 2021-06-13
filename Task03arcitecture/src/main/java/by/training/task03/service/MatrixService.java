package by.training.task03.service;

import by.training.task03.service.exception.ServiceException;

public interface MatrixService {
    double[][] randomIni(int lenght, int width,int minValue,int maxValue) throws ServiceException;
    double[][] matrixCreator(String[] matrix,int columns,int rows) throws ServiceException;
    double[][] transposition(double[][] matrix);
    double[][] multiplication(double[][] matrix1,double[][] matrix2) throws ServiceException;
    double[][] multiplicationOnNum(double[][] matrix,int num);
    double[][] sumMatrix(double[][] matrix1,double[][] matrix2) throws ServiceException;
    double[][] matrixSubtraction(double[][] matrix1,double[][] matrix2) throws ServiceException;
}
