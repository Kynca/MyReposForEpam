package by.epam.task2.cycles;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FunctionCalculation {

    static final Logger rootLogger= LogManager.getRootLogger();

    /**
     * count the maths function square of (sinx) in the x interval from floor to celling
     * @param floor lower value
     * @param celling highest value
     * @param step step of cycle
     * @return boolean status of function calculation
     */
    public boolean calculation(double floor,double celling, double step){
    if(celling<floor){
        rootLogger.error("wrong number input");
        throw new IllegalArgumentException("floor can not be more than celling");
    }
    for(double i=floor;i<celling;i+=step){
        System.out.println(i+" "+Math.pow(Math.sin(i),2));
    }
    return true;
}
}
