package by.epam.task2.cycles.testing;

import by.epam.task2.cycles.NumberTransformation;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

public class TransformationTest {
    NumberTransformation transformer = new NumberTransformation();

    @DataProvider(name = "Transferable rom number")
    public Object[][] romScenario() {
        return new Object[][]{
                {new char[]{'X','I','I'},12},
                {new char[]{'D','X','L','I','V'},544},
                {new char[]{'C','M','X','C','V','I'},996},
                {new char[]{'M','M','M','D','C','C','L','V','I','I','I'},3758},
                {new char[]{'I'},1}
        };
    }

    @Test(description = "This is a correct data in correct order and i hope it will pass the test",
    dataProvider ="Transferable rom number" )
    public void transTest(char[] romNums,int result){
        assertEquals(transformer.romToArab(romNums),result);
    }

    @DataProvider(name = "Check on exception")
    public Object[][] notRomScenario() {
        return new Object[][]{
                {new char[]{'I','I','X'},12},
                {new char[]{'D','X','L','I','A'},544},
                {new char[]{'a','d','d','x','a','a'},996},
                {new char[]{'M','M','X','M'},3758}
        };
    }

    @Test(description = "This is a correct data in correct order and i hope it will pass the test",
            dataProvider ="Check on exception",expectedExceptions = {IllegalArgumentException.class})
    public void incorrectDataTest( char[] romNums,int result) {
        assertEquals(transformer.romToArab(romNums),result);
    }
}
