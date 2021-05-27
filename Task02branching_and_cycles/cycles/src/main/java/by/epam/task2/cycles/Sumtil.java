package by.epam.task2.cycles;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Sumtil {
    static final Logger rootLogger= LogManager.getRootLogger();
    /**
     *
     * @param limit  summation boundary
     * @return sum from 1+2+....+limit
     */
    public int sumUntil(int limit){
        if(limit<=0) {
            rootLogger.error("User enter negative limit");
            throw new IllegalArgumentException("limit can not be negative");
        }
        int result=0;
        for(int i=0;i<=limit;i++){
            result+=i;
        }
        return result;
    }
}
