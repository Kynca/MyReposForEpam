package by.epam.task2_branching.testing;

import by.epam.task2_branching.ValueRedistribution;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class ValueRedistributionTest {
    ValueRedistribution redistribution= new ValueRedistribution();

    @DataProvider(name="possible scenario")
    public Object[][] allScenario(){
        return new Object[][]{
                {new int[]{1,2},"x=2 y=1"},
                {new int[]{0,2},"x=2 y=0"},
                {new int[]{3,2},"x=3 y=2"},
                {new int[]{-11,2},"x=2 y=-11"},
                {new int[]{-1,-2},"x=-1 y=-2"},
        };
    }

    @Test(dataProvider ="possible scenario",description = "all scenario")
    public void redistributionValueTest(int[]xy,String result){
        assertEquals(redistribution.redistribution(xy[0],xy[1]),result);
    }
}
