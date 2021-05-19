package by.training.task01.ex6;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;
/**
 * @author Kunitsiy Ilya
 * @version 11.0.10
 * This is MilkAndCans where completes exercise: if in n small cans 80 liters of milk
 * how many liters in m big cans
 */

public class MilkAndCans {

    static final Logger milkInCan = LogManager.getLogger(MilkAndCans.class.getName());

    /**
     * It is main method where creates MilkAndCans class, then user input value for small cans and big cans amount
     * then show the result of calculation
     */
    public static void main(String[] args) {
        MilkAndCans mac=new MilkAndCans();
        System.out.println("How many small Cans?");
        int smallCans=mac.canInput(80);
        System.out.println("How many big Cans?");
        int bigCans=mac.canInput(80);
        milkInCan.info("smallcans= "+smallCans+" bigCans="+bigCans);
        System.out.println(mac.bigCanCalculation(smallCans,bigCans)+" milk in " +bigCans+" bigCans");
    }

    /**
     * This is constant which store how many liters of milk in small cans
     */
    static final double MILK_AMOUNT=80;

    /**
     * This method calculate liters in big cans
     * @param n is how many small cans
     * @param m is how many big cans
     * @return how many liters in m big cans
     */
    public double bigCanCalculation(int n, int m){
        if(n<=0||m<=0||n>80)return -1;
        return (MILK_AMOUNT/n+12)*m;
    }

    /**
     * This method read user's input and check is it positive number and not more than ceiling
     * if it's not user should input another number until it is correct
     * @param limiter is input ceiling
     * @return positive number which passed check and can be used in calculation
     */
    public int canInput(int limiter){
        int number;
        Scanner scan = new Scanner(System.in);
        while(true){
            if(scan.hasNextInt()){
                number=scan.nextInt();
            }else{
                System.out.println("Enter number! ");
                scan.next();
                continue;
            }
            if(number>0&&number<limiter){
                break;
            }
            else{
                milkInCan.info("Wrong data "+number);
                System.out.println("Enter positive number!");
            }
        }
        return number;
    }
}
