package by.training.task01.ex38runner;
import by.training.task01.ex38.IsInArea;


public class AreaCheckRunner {
    public static void main(String[] args) {
        IsInArea iia=new IsInArea();
        System.out.println("x= ");
        int x=iia.numberChecker(Integer.MIN_VALUE,Integer.MAX_VALUE);
        System.out.println("y= ");
        int y =iia.numberChecker(Integer.MIN_VALUE,Integer.MAX_VALUE);
        boolean result=iia.isInArea(x,y);
        System.out.println("Does x and y in figure? Result: "+result);
    }
}
