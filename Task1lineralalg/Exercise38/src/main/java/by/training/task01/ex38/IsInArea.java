package by.training.task01.ex38;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * @author Kunitsiy Ilya
 * @version 11.0.10
 * This is IsInArea where checks is x and y in chosen area
 */
public class IsInArea {
    static final Logger areaCheckLogger= LogManager.getLogger(IsInArea.class.getName());
    /**
     * const point coordinates in triangle and figure 1
     */
    static final int[][] TRIANGLE={{0}, {-1,0,1},{-2,-1,0,1,2},{-3,-2,-1,0,1,2,3},{-4,-3,-2,-1,0,1,2,3,4}};
    static final int[][] FIGURE1={{-2,-1,0,1,2},{-2,-1,0,1,2},{-2,-1,0,1,2},{-2,-1,0,1,2},{-4,-3,-2,-1,0,1,2,3,4},{-4,-3,-2,-1,0,1,2,3,4},{-4,-3,-2,-1,0,1,2,3,4}};

    /**
     * isInArea gets x and y coordinates and check is this point in chosen figure if not return false
     * @param x x coordinate
     * @param y y coordinate
     * @return result of check
     */
    public boolean isInArea(int x,int y) {
        int choice;
        boolean result=false;
        System.out.println("Check is x and y in :\n" + "1.Triangle\n"+ "2.Figure with right angles\n"+
                "3.Figure like unfinished strange circle");
        choice=numberChecker(0,4);
        /**
         * user choose figure for check
         */
        switch (choice) {
            case 1:
                areaCheckLogger.info("User chose Triangle");
                if(y>=0&&y<=TRIANGLE.length&&x>=TRIANGLE[y][0]&&x<=TRIANGLE[y].length){
                  result=true;
                }
                break;
            case 2:
                areaCheckLogger.info("User chose figure with right angles");
                if(y<0){
                    y=3-y;
                }
                if(y>=0&&y<=FIGURE1.length&&x>=FIGURE1[y][0]&&x<=FIGURE1[y].length){
                     result=true;
                }
                break;
            case 3:
                areaCheckLogger.info("User chose figure like unfinished strange circle");
                if ((x < 4 && y < 4 && x > 0 && y > 0 && Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(4,2))||
                        (x <= 5 && y <= 0 && x >= 0 && y >= -5 && Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(5,2)))
                    result=true;
                break;
        }
        areaCheckLogger.info("Result of check is "+result);
        return result;
    }
    /**
     * This function read user's input and check is it positive number and in [min,max]
     * if it's not user should input another number until it is correct
     * @param min floor
     * @param max ceiling
     * @return positive number which passed check and can be used in calculation
     */
    public int numberChecker(int min,int max){
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
            if(number>min&&number<max) {
                areaCheckLogger.info("User enter "+number);
                break;
            }
            else{
                areaCheckLogger.info("User enter wrong data");
                System.out.println(" Number must be from "+min+" to "+max);
            }
        }
        return number;
    }
}
