package by.epam.task2_branching.testing;

import by.epam.task2_branching.RealNumberComparison;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class RealComparatorTest {
    RealNumberComparison realComparison=new RealNumberComparison();

    @DataProvider(name = "suit to conditions")
    public Object[][] goodScenarios(){
        return new Object[][]{
                {new double[]{10,3,1},"fistNumber=100.0 secondNumber=9.0 thirdNumber=1.0" },
                {new double[]{155,10,0},"fistNumber=24025.0 secondNumber=100.0 thirdNumber=0.0" },
                {new double[]{1,0,-2},"fistNumber=1.0 secondNumber=0.0 thirdNumber=4.0" },
                {new double[]{-4,-5,-6},"fistNumber=16.0 secondNumber=25.0 thirdNumber=36.0" },
        };
    }
    @Test(description = "scenario with ascending numbers",dataProvider = "suit to conditions")
    public void goodScenarioCheck(double[] nums,String result){
        assertEquals(realComparison.numberCompare(nums[0],nums[1],nums[2]),result);
    }

    @DataProvider(name="alternativ scenario")
    public Object[][] altScenario(){
        return new Object[][]{
                {new double[]{2,3,-6},"fistNumber=2.0 secondNumber=3.0 thirdNumber=6.0" },
                {new double[]{0,0,155.3},"fistNumber=0.0 secondNumber=0.0 thirdNumber=155.3" },
                {new double[]{1.1,-1.2,1.6},"fistNumber=1.1 secondNumber=1.2 thirdNumber=1.6" },
                {new double[]{-4,-2,6},"fistNumber=4.0 secondNumber=2.0 thirdNumber=6.0" },
        };
    }
    @Test(description = "scenario with numbers in random order",dataProvider = "alternativ scenario")
    public void altScenarioCheck(double[] nums,String result){
        assertEquals(realComparison.numberCompare(nums[0],nums[1],nums[2]),result);
    }

}
