package test.resistancetesting.action;

import static java.lang.Double.NaN;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.training.task01.ex30.ResistanceCalculation;

public class ResCalcTest {

    ResistanceCalculation resCalc = new ResistanceCalculation();

    @DataProvider(name = "positiveValueOfResistance")
    public Object[][] createPositiveResistance() {
        return
                new Object[][]{
                        {new double[]{2, 1, 1}, 0.4},
                        {new double[]{7, 4, 2}, 1.12},
                        {new double[]{100, 4, 3}, 1.6853932584269662},
                        {new double[]{0.2, 4, 1}, 0.16},
                };
    }

    @DataProvider(name = "negativeValueOfResistance")
    public Object[][] createNegativeResistance() {
        return
                new Object[][]{
                        {new double[]{-1, 1, 1}, -1},
                        {new double[]{-1, -20, -219}, -1},
                        {new double[]{100, 4, -30000}, -1},
                        {new double[]{0.2, -1, 1}, -1},
                };

    }

    @DataProvider(name ="zeroValueOfResistance")
    public Object[][] createZeroResistance(){
        return
                new Object[][]{
                        {new double[]{0,0,0},NaN},
                };
    }

    @Test(description = "Positive scenary of resistance calculation",
            dataProvider = "positiveValueOfResistance")
    public void testPositiveValueCalc(double []res,  double c) {
        double actual = resCalc.resistanceCalculation(res[0],res[1],res[2]);
        assertEquals(actual, c,0.00001);
    }

    @Test(description = "Negative scenary of resistance calculation",
            dataProvider = "negativeValueOfResistance")
    public void testNegativeValueCalc(double []res,  double c) {
        double actual = resCalc.resistanceCalculation(res[0],res[1],res[2]);
        assertEquals(actual, c);
    }

    @Test(description = "Zero scenary of resistance calculation",
            dataProvider = "zeroValueOfResistance",
    expectedExceptions = {IllegalArgumentException.class})
    public void testZeroValueCalc(double []res,  double c) {
        double actual = resCalc.resistanceCalculation(res[0],res[1],res[2]);
        assertEquals(actual, c);
    }
}