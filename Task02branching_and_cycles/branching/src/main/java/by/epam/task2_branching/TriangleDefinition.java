package by.epam.task2_branching;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class have function for definitions is this 2 angles allow to
 * create a triangle and is it right
 */
public class TriangleDefinition {
    static final Logger rootLogger= LogManager.getRootLogger();

    public String isRightTriangle(int angle1,int angle2){
        if(angle1<=0||angle2<=0){
            rootLogger.error("User enter wrong data");
            throw new IllegalArgumentException("Illegal data for angles");
        }
        if(angle1+angle2<180){
            if(angle1+angle2==90||angle1==90||angle2==90){
                return "It is a right triangle";
            }else{
                return "It isn't right triangle";
            }
        }else{
            return "Triangle with "+angle1+" and "+angle2+" angles can not exist";
        }
    }
}
