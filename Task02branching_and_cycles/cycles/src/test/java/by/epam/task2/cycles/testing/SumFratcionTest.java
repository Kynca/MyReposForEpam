package by.epam.task2.cycles.testing;

import by.epam.task2.cycles.SumFractionUntil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SumFratcionTest {
    SumFractionUntil sumFrac = new SumFractionUntil();

    @DataProvider(name="good scenario")
    public Object[][] goodScenario(){
        return new Object[][]{
                {1,1.0},
                {2,1.5},
                {5,2.283}
        };
    }

    @Test(description = "user input a correct data",dataProvider = "good scenario")
    public void goodScenario(int limit,double result){
        assertEquals(sumFrac.sumTill(limit),result,0.001);
    }

    @DataProvider(name="bad scenario")
    public Object[][] badScenario(){
        return new Object[][]{
                {-1,"limit can not be negative"},
                {0,"limit can not be negative"},
                {-100,"limit can not be negative"},
        };
    }

    @Test(description = "user input incorrect data",dataProvider = "bad scenario",
            expectedExceptions = {IllegalArgumentException.class})
    public void badScenario(int limit,double result){
        assertEquals(sumFrac.sumTill(limit),result);
    }

}
