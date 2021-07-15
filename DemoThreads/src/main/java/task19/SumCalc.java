package task19;

import java.util.concurrent.Callable;

public class SumCalc implements Callable<Number> {

    private double[] array;

    public SumCalc(double[] array){
        this.array = array;
    }

    @Override
    public Number call() {
        double result = 0;
        for (int i = 0; i < array.length; i++){
            result += array[i];
        }
        System.out.println("ended calc with result " + result);
            return result;
    }
}
