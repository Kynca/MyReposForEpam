package by.epam.task2.cycles.testing;

import by.epam.task2.cycles.ArithmeticProgressionCheck;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class ArithmeticProgressionCheckTest {
    ArithmeticProgressionCheck isProgression= new ArithmeticProgressionCheck();

    @DataProvider(name="is a progression")
    public Object[][] progression(){
        return new  Object[][]{
                {123,true},
                {246,true},
                {123456789,true},
                {13579,true},
                {2468,true},
                {-147,true},
        };
    }

    @Test(description = "There is a scenario with good ending",dataProvider = "is a progression")
    public void isProgression(int number,boolean result){
        assertEquals(isProgression.progressionCheck(number),result);
    }

    @DataProvider(name="is not a progression")
    public Object[][] notProgression(){
        return new  Object[][]{
                {123,false},
                {246,false},
                {13458,false},
                {32112,false},
                {788,false},
                {111,false},
        };
    }

    @Test(description = "There is a scenario with good ending",dataProvider = "is a progression")
    public void isNotProgression(int number,boolean result){
        assertEquals(isProgression.progressionCheck(number),result);
    }

    @DataProvider(name="bad scenario")
    public Object[][] badScenario(){
        return new Object[][]{
                {-1,"Incorrect data"},
                {0,"Incorrect data"},
                {-99,"Incorrect data"},
                {71,"Incorrect data"},
        };
    }

    @Test(description = "user input incorrect data",dataProvider = "bad scenario",
            expectedExceptions = {IllegalArgumentException.class})
    public void badScenario(int number,boolean result){
        assertEquals(isProgression.progressionCheck(number),result);
    }
}
