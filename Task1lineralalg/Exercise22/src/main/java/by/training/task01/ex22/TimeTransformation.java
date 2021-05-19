package by.training.task01.ex22;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * @author Kunitsiy Ilya
 *  @version 11.0.10
 * TimeTransformation class have main method, timeInsert method and time calculation method
 * this class transform seconds in HH:MM:SS format
 */
public class TimeTransformation {
    static final Logger ttLogger =LogManager.getLogger(TimeTransformation.class.getName());

    /**
     * main method which creates TimeTransformation class and get input,transform and show the result of transformation
     */
    public static void main(String[] args) {
        TimeTransformation tt= new TimeTransformation();
        String result=tt.timeCalculation(tt.timeInsert());
        ttLogger.info("Result of transformation is"+result);
        System.out.println(result);
    }

    /**
     * Input method which check is entered data is correct
     * @return amount of seconds which user have entered
     */
    public int timeInsert() {
        Scanner scan = new Scanner(System.in);
        int time;
        System.out.println("Enter seconds ");
        while(true){
            if (scan.hasNextInt()) {
                time = scan.nextInt();
            } else {
                System.out.println("Enter number! ");
                scan.next();
                continue;
            }

            if (time >= 0) {
                ttLogger.info("User enter correct data: "+time+"seconds");
                break;
            }
            else {
                System.out.println("Enter positive number!");
                ttLogger.info("User enter wrong data");
            }
        }
        return time;
    }

    /**
     * This is transformation seconds into HH:MM:SS format
     * @param time amount of seconds
     * @return String with transformation result
     */
    public String timeCalculation(int time){

        if(time<0){
            return "Time cannot be negative";
        }

        int hours=time/3600;
        time-=3600*hours;
        int minutes=time/60;
        time-=60*minutes;
        int seconds=time;
        return "Time is: "+hours+":"+minutes+":"+seconds;
    }
}

