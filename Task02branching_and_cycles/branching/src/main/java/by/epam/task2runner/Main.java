package by.epam.task2runner;
import by.epam.task2_branching.*;
import by.epam.task2service.InputChecker;


public class Main {

    public static void main(String[] args) {

        String result;
        InputChecker inputChecker=new InputChecker();

        GreatestNumber greatestNumber = new GreatestNumber();
        System.out.println("Enter 2 numbers");
        System.out.println("Greatest number is:"+
                greatestNumber.numberComparator(inputChecker.intInput(), inputChecker.intInput()));

        TriangleDefinition triangleDef = new TriangleDefinition();
        System.out.println("Enter 2 angles");
        result=triangleDef.isRightTriangle(inputChecker.positiveIntInput(), inputChecker.positiveIntInput());
        System.out.println(result);

        ValueRedistribution redistribution= new ValueRedistribution();
        System.out.println("Enter x and y:");
        result=redistribution.redistribution(inputChecker.intInput(), inputChecker.intInput());
        System.out.println(result);

        RealNumberComparison numberComparison = new RealNumberComparison();
        System.out.println("Enter 3 real number");
        result =numberComparison.numberCompare(inputChecker.doubleInput(),inputChecker.doubleInput(),inputChecker.doubleInput());
        System.out.println(result);

        FunctionCalculation functionCalculation = new FunctionCalculation();
        System.out.println("Enter x value");
        System.out.println("result of calculation is: "+functionCalculation.calculation(inputChecker.doubleInput()));
    }
}
