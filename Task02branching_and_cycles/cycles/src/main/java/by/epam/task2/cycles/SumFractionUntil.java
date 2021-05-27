package by.epam.task2.cycles;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SumFractionUntil {
    static final Logger rootLogger= LogManager.getRootLogger();

    /**
     *
     * @param limit  summation boundary
     * @return sum from 1/1+1/2+....1/limit
     */
   public double sumTill(int limit) {
       if (limit <= 0){
           rootLogger.error("User enter negative limit");
           throw new IllegalArgumentException("limit can not be negative");
   }
       double result=0;
       for(int i=1;i<=limit;i++){
           result+=1.0/i;
       }
       return result;
   }
}
