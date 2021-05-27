package by.epam.task2service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * This class have all input functions which is used in this module
 */
public class InputChecker {

    static final Logger rootLogger= LogManager.getRootLogger();

    public int intInput(){
        Scanner scan=new Scanner(System.in);
        while(true){
            if(scan.hasNextInt()){
                int result=scan.nextInt();
                rootLogger.info("User enter "+result);
                return result;
            }else{
                rootLogger.error("Wrong data for this task");
                System.out.println("It must be integer!");
                scan.next();
            }
        }
    }

    public int positiveIntInput(){
        int result=intInput();
        while (true) {
            if (result > 0){
                return result;
            }else{
                rootLogger.error("Wrong data for this task");
                System.out.println("Number must be positive!");
                result=intInput();
            }
        }
    }

    public double doubleInput(){
        Scanner scan=new Scanner(System.in);
        while(true){
            if(scan.hasNextDouble()){
                double result=scan.nextDouble();
                rootLogger.info("User enter "+result);
                return result;
            }else{
                rootLogger.error("Wrong data for this task");
                System.out.println("It must be a number!");
                scan.next();
            }
        }
    }

}
