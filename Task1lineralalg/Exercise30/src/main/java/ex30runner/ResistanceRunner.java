package ex30runner;
import by.training.task01.ex30.ResistanceCalculation;

public class ResistanceRunner {
    public static void main(String[] args) {
        ResistanceCalculation resCalc=new ResistanceCalculation();
        double result= resCalc.resistanceCalculation(resCalc.resistanceInput(), resCalc.resistanceInput(), resCalc.resistanceInput());
        System.out.println("Connection resistance="+ result);
    }

}
