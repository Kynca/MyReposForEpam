package by.training.task01.ex14runner;
import by.training.task01.ex14.Circle;

public class CircleRunner {
    public static void main(String[] args) {
        Circle circle = new Circle();
        double rad= circle.radInput();
            double area=circle.circleArea(rad);
            double circumference= circle.circumference(rad);
            System.out.println("Area of circle = "+area+" circumference"+circumference);
    }
}
