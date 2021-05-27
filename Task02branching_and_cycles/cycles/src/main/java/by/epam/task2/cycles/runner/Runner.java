package by.epam.task2.cycles.runner;

import by.epam.task2.cycles.*;
import by.epam.task2.cycles.service.InputChecker;

public class Runner {

    public static void main(String[] args) {
        InputChecker input= new InputChecker();

        NumberTransformation transformation= new NumberTransformation();
        System.out.println("result of transformation is "+transformation.romToArab(input.charInput()));

        System.out.println("Enter number more than 100");
        ArithmeticProgression ap= new ArithmeticProgression();
        System.out.println("Is it arithmetic progression?  "+ap.progressionCheck(input.intInput()));

        FunctionCalculation calculation = new FunctionCalculation();
        calculation.calculation(input.doubleInput(1),input.doubleInput(2),input.doubleInput(3));

        SumFractionUntil fractionSum= new SumFractionUntil();
        System.out.println("result of fraction sum "+fractionSum.sumTill(input.positiveIntInput()));

        Sumtil sum= new Sumtil();
        System.out.println("result of sum "+sum.sumUntil(input.positiveIntInput()));
    }
}
