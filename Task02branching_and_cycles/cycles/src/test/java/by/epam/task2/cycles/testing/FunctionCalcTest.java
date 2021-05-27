package by.epam.task2.cycles.testing;

import by.epam.task2.cycles.FunctionCalculation;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FunctionCalcTest {
    FunctionCalculation fanCalc=new FunctionCalculation();

    @DataProvider(name="good scenario")
    public Object[][] goodScenario(){
        return new Object[][]{
                {new double[]{1,2,2},true},
                {new double[]{0,10,2},true},
                {new double[]{3,6,1},true},
                {new double[]{8,68.1,2},true},
                {new double[]{1.3,7.1,6.1},true}
        };
    }

    @Test(description = "user input a correct data",dataProvider = "good scenario")
    public void goodScenario(double[]data,boolean result){
        assertEquals(fanCalc.calculation(data[0],data[1],data[2]),result);
    }

    @DataProvider(name="bad scenario")
    public Object[][] badScenario(){
        return new Object[][]{
                {new double[]{1,2,2},"Exception"},
                {new double[]{0,10,2},"Exception"},
                {new double[]{3,6,1},"Exception"},
                {new double[]{8,68.1,2},"Exception"},
                {new double[]{1.3,7.1,6.1},"Exception"}
        };
    }

    @Test(description = "user input incorrect data",dataProvider = "bad scenario",
            expectedExceptions = {IllegalArgumentException.class})
    public void badScenario(double[]data,boolean result){
        assertEquals(fanCalc.calculation(data[0],data[1],data[2]),result);
    }

}
