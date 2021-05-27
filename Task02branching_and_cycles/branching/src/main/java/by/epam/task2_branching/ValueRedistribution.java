package by.epam.task2_branching;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * simple function for finding greatest number
 * and assign the result of greatest number to x
 */
public class ValueRedistribution {

    static final Logger rootLogger= LogManager.getRootLogger();

    public String redistribution(int x,int y){
        if(x<y){
            int temp=x;
            x=y;
            y=temp;
        }
        rootLogger.info("x and y= "+x+","+y);
        return "x="+x+" y="+y;
    }
}
