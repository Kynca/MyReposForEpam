package by.epam.task2.cycles;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArithmeticProgressionCheck {

    static final Logger rootLogger= LogManager.getRootLogger();

    /**
     * @param number number for checking on arithmetic progression
     * @return boolean true if it progression false in other case
     */
    public boolean progressionCheck(int number){
        number=Math.abs(number);
        int amount=numberAmount(number);
        if(amount<3){
            rootLogger.error("wrong number");
            throw new IllegalArgumentException("Number must be more than or equal 100");
        }
        int step=step(number,amount);
        while (amount>1){
            number=number-(int)(Math.pow(10,amount-1))*(int)(number/Math.pow(10,amount-1));
            amount--;
          if(amount==1){
              return true;
          }else{
              if(step!=step(number,amount))return false;
          }
        }
        rootLogger.info("result of checking is it progression = true");
        return true;
    }

    /**
     * @param number
     * @return int number of digits in number
     */
    private int numberAmount(int number){
        int counter=1;
        while(number/10>0){
            number/=10;
            counter++;
        }
        return counter;
    }

    /**
     * @param number
     * @param amount number of digits in number
     * @return arithmetic step
     */
    private int step(int number,int amount){
        int temp=(int)(number/Math.pow(10,amount-1));
        number=   number-(int)(Math.pow(10,amount-1))*(int)(number/Math.pow(10,amount-1));
        temp= (int) (number/Math.pow(10,amount-2))-temp;
        return temp;
    }
}
