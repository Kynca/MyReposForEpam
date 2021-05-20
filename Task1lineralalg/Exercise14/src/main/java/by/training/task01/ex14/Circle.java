package by.training.task01.ex14;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * @author Kunitsiy Ilya
 * @version 11.0.10
 * Circle class with main method and functions for calculating area and circumference
 */
public class Circle {

    static final Logger circleLogger =LogManager.getLogger(Circle.class.getName());
    /**
     * circle area calculation function
     * @param rad circle radius
     * @return calculated circle area
     */
    public double circleArea(double rad){
        if(rad<0){
            circleLogger.error("wrong data negative radius");
            return -1;
        }
        double S;
        S=3.14*Math.pow(rad,2);
        circleLogger.info("result of circle area calculation is"+S);
        return S;
}

    /**
     * circumference calculation function
     * @param rad circle radius
     * @return calculated circumference
     */

    public double circumference(double rad) {
        if(rad<0){
            circleLogger.error("wrong data negative radius");
            return -1;
        }
        double P;
        P=2*3.14*rad;
        circleLogger.info("result of circlecircumference is"+P);
        return P;
    }

    /**
     * radius input function where user enter number and then function check is it number and it bigger than 0
     * if not user should enter another data.
     * @return checked radius
     */

    public double radInput(){
        double rad;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter radius ");
        while(true){
            if(scan.hasNextDouble()){
                rad=scan.nextDouble();
                circleLogger.info("User enter this value for rad="+rad);
                break;
            }else{
                System.out.println("Enter number! ");
                circleLogger.info("User enter wrong data");
                scan.next();
            }
        }
        return rad;
    }

}
