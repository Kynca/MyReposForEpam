package by.epam.task2.cycles.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * This class have all input functions which is used in this module
 */

public class InputChecker {

    static final Logger rootLogger= LogManager.getRootLogger();

    public int intInput() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            if (scan.hasNextInt()) {
                int result=scan.nextInt();
                rootLogger.info("User enter "+result);
                return result;
            } else {
                System.out.println("It must be integer!");
                rootLogger.error("User enter wrong data");
                scan.next();
            }
        }
    }

    public int positiveIntInput() {
        System.out.println("Enter limit");
        int result = intInput();
        while (true) {
            if (result > 0) {
                return result;
            } else {
                rootLogger.error("User enter wrong data");
                System.out.println("Number must be positive!");
                result = intInput();
            }
        }
    }

    public double doubleInput(int counter) {
        switch (counter){
            case 1:
            System.out.println("Enter floor");
            break;
            case 2:
                System.out.println("Enter celling");
                break;
            case 3:
                System.out.println("Enter step");
                break;
            default:
                System.out.println("Enter a real number");
        }

        Scanner scan = new Scanner(System.in);
        while (true) {
            if (scan.hasNextDouble()) {
               double result= scan.nextDouble();
                rootLogger.info("User enter "+result);
                return result;
            } else {
                System.out.println("It must be a number!");
                rootLogger.error("User enter wrong data");
                scan.next();
            }
        }

    }

    public char[] charInput(){
        System.out.println("Enter rom numbers for example MMMX");
        Scanner scan = new Scanner(System.in);
        return scan.next().toCharArray();
    }
}
