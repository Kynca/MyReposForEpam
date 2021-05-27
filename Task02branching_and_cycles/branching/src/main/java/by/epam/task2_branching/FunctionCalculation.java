package by.epam.task2_branching;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * if x more than 0 and less than 3 result will be doubled x. In other case result is always 4
 */
public class FunctionCalculation{

    static final Logger rootLogger= LogManager.getRootLogger();

    public double calculation(double x){
        if(x>=0&&x<=3){
            rootLogger.info("x doubles");
            return x*x;
        }else{
            rootLogger.info("result of calc is 4");
            return 4;
        }
    }
}
