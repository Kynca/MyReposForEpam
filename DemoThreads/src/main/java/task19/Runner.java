package task19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Runner {

    public static void main(String[] args) {
        double[] array = {2.6, 3.2, 7.3, 10.45, 400.2, 30.1, -10.3, 72, 16, 49, 50, 15, 51, 92, 53, 67, 83, 29, 8, 65, 23, 7,
                34, 21, 44, 82, 6, 9, 70, 57, 94, 11, 49, 97, 96, 10, 38, 25, 68, 52, 9, 3.47, 9, 6, 3.2, 8, 72, 66, 90, 76};
        int last = array.length % 8;
        int index = 0;

        ArrayList<Future<Number>> list = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(8);

        for (int i = 0; i < 8; i++) {
            if (i < 7) {
                list.add(executor.submit(new SumCalc(Arrays.copyOfRange(array, index, index += 8))));
            } else {
                list.add(executor.submit(new SumCalc((Arrays.copyOfRange(array, array.length - last, array.length)))));
            }
        }
        executor.shutdown();
        double result=0;
        for (Future<Number> number : list) {
            try {
                result +=  number.get().doubleValue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println(result);
    }

}
