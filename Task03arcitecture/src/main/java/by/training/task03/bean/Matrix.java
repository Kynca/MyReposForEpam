package by.training.task03.bean;

import java.util.Arrays;

public class Matrix {
    double[][]matrix;
    public Matrix(double[][]matrix){
        this.matrix=matrix;
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "matrix=" + Arrays.deepToString(matrix) +
                '}';
    }

}
