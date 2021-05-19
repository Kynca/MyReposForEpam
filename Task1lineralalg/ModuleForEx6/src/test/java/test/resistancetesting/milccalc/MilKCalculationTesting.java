package test.resistancetesting.milccalc;
import static org.testng.Assert.assertEquals;

import by.training.task01.ex6.MilkAndCans;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MilKCalculationTesting {
    MilkAndCans mac = new MilkAndCans();

    @DataProvider(name = "positiveNumberOfCans")
    public Object [][] fillPositiveDataOfCans(){
        return new Object[][]{
                {new int[]{8,10},220},
                {new int[]{3,13},502.66666666666674},
                {new int[]{80,2},26},
                {new int[]{40,1000},14000}
        };
    }

    @DataProvider(name = "negativeAndZeroAndNotInLimitsNumberOfCans")
    public Object [][] fillNegativeAndZeroDataOfCans(){
        return new Object[][]{
                {new int[]{-1,0},-1},
                {new int[]{0,-1},-1},
                {new int[]{-1,-1},-1},
                {new int[]{0,0},-1},
                {new int[]{81,1},-1},
                {new int[]{81,-1},-1},
                {new int[]{81,0},-1},
                {new int[]{-1,1},-1},
                {new int[]{1,-1},-1},
                {new int[]{0,1},-1},
                {new int[]{1,0},-1},
        };
    }

    @Test(description = "Check scenary with normal data",
    dataProvider = "positiveNumberOfCans")
    public void positiveAmountOfCans(int nm[],double result){
        double actual=mac.bigCanCalculation(nm[0],nm[1]);
        assertEquals(actual,result);
    }

    @Test(description = "Check scenary with wrong data",
            dataProvider = "negativeAndZeroAndNotInLimitsNumberOfCans")
    public void wrongAmountOfCans(int nm[],double result){
        double actual=mac.bigCanCalculation(nm[0],nm[1]);
        assertEquals(actual,result);
    }
}
