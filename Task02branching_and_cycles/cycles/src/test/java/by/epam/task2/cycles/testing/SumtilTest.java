package by.epam.task2.cycles.testing;

import by.epam.task2.cycles.Sumtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class SumtilTest {
    Sumtil sum=new Sumtil();

    @DataProvider(name="good scenario")
    public Object[][] goodScenario(){
        return new Object[][]{
                {1,1},
                {2,3},
                {5,15},
        };
    }

    @Test(description = "user input a correct data",dataProvider = "good scenario")
    public void goodScenario(int limit,int result){
        assertEquals(sum.sumUntil(limit),result);
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
    public void badScenario(int limit,String result){
        assertEquals(sum.sumUntil(limit),result);
    }
}
