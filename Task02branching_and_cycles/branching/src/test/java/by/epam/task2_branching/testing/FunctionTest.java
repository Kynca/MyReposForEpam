package by.epam.task2_branching.testing;
import by.epam.task2_branching.FunctionCalculation;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;


public class FunctionTest {
FunctionCalculation funcCalc= new FunctionCalculation();

@DataProvider(name="All possible scenarios")
    public Object [][] possibleScenarios(){
            return new Object[][]{
                    {1,1},
                    {2,4},
                    {-100,4},
                    {1000,4},
                    {3,9},
                    {0,0},
                    {2.5,6.25}
            };
    }

    @Test(description = "testing all scenarios",dataProvider ="All possible scenarios")
    public void testFunc(double x,double result){
    assertEquals(funcCalc.calculation(x),result);
    }
}
