package by.training.task01.ex30;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * @author Kunitsiy Ilya
 * @version 11.0.10
 * This is ResistanceCalculation class where calculates resistance connection
 */
public class ResistanceCalculation {
    static final Logger resLogger =LogManager.getLogger(ResistanceCalculation.class.getName());
    public static void main(String[] args) {
        ResistanceCalculation rescalc=new ResistanceCalculation();
       double result= rescalc.resistanceCalculation(rescalc.resistanceInput(), rescalc.resistanceInput(), rescalc.resistanceInput());
       resLogger.info("result of calculation - "+result);
        System.out.println("Connection resistance="+ result);
    }

    /**
     * @param R1 first resistance indicator
     * @param R2 second resistance indicator
     * @param R3 third resistance indicator
     * @return connection resistance result
     */
   public double resistanceCalculation(double R1,double R2,double R3){
       if(R1<0||R2<0||R3<0)return -1;
       double result;
       result=R1*R2*R3/(R1*R2+R2*R3+R3*R1);
       return result;
   }

    /**
     * counter is used for counting how many times resistanceInput was started
     */
   int counter=0;

    /**
     * This function check it it correct data or not
     * if now user must input another data until it's correct
     * @return checked resistance value
     */
   public double resistanceInput(){
       double number;
       Scanner scan = new Scanner(System.in);
       System.out.println("Enter the "+(++counter)+"st resistance value");
       while(true){
           if(scan.hasNextDouble()){
               number=scan.nextDouble();
           }else{
               System.out.println("Enter number! ");
               scan.next();
               continue;
           }
           if(number>0) {
               resLogger.info(counter+"st resistance value is "+number);
               break;}
           else{
               resLogger.info("User enter wrong value");
               System.out.println("Enter positive number!");
           }
       }
       return number;
   }
}
