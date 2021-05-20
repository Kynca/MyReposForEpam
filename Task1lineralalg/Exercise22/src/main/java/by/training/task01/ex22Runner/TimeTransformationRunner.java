package by.training.task01.ex22Runner;
import by.training.task01.ex22.TimeTransformation;

public class TimeTransformationRunner {
    public static void main(String[] args) {
        TimeTransformation tt= new TimeTransformation();
        String result=tt.timeCalculation(tt.timeInsert());
        System.out.println(result);
    }
}
