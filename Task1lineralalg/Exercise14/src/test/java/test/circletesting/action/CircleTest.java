package test.circletesting.action;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.training.task01.ex14.Circle;

public class CircleTest {
    private Circle circ = new Circle();

    @DataProvider(name = "positiveDataForCircleArea")
    public Object[][] createPositiveDataForCircleArea() {
        return new Object[][]{
                {2, 12.56},
                {3, 28.26},
                {1,3.14}
        };
        }

    @DataProvider(name = "positiveDataForCircumference")
    public Object[][] createPositiveDataForCircumference() {
        return new Object[][]{
                {2, 12.56},
                {3, 18.84},
                {1,6.28}
        };
    }

    @DataProvider(name = "negativeDataForCircleArea")
    public Object[][] createNegativeDataForCircleArea() {
        return new Object[][]{
                {-1, -1},
                {-2010, -1}
        };
    }

    @DataProvider(name = "negativeDataForCircumference")
    public Object[][] createNegativeDataForCircumference() {
        return new Object[][]{
                {-1, -1},
                {-20, -1}
        };
    }

    @DataProvider(name = "zeroDataForCircleArea")
    public Object[][] createZeroDataForCircleArea() {
        return new Object[][]{
                {0, 0}
        };
    }

    @DataProvider(name = "zeroDataForCircumference")
    public Object[][] createZeroDataForCircumference() {
        return new Object[][]{
                {0, 0}
        };
    }

    @Test(description = "Positive scenary of the area calculation",
            dataProvider = "positiveDataForCircleArea")
    public void testPositiveAreaCalc(int rad,  double c) {
        double actual = circ.circleArea(rad);
        assertEquals(actual, c);
    }

    @Test(description = "Positive scenary of the circumference calculation",
            dataProvider = "positiveDataForCircumference")
    public void testPositiveCircumferenceCalc(int rad,  double c) {
        double actual = circ.circumference(rad);
        assertEquals(actual, c);
    }

    @Test(description = "Negative scenary of the area calculation",
            dataProvider = "negativeDataForCircleArea")
    public void testNegativeAreaCalc(int rad,  double c) {
        double actual = circ.circleArea(rad);
        assertEquals(actual, c);
    }

    @Test(description = "Negative scenary of the circumference calculation",
            dataProvider = "negativeDataForCircumference")
    public void testNegativeCircumferenceCalc(int rad,  double c) {
        double actual = circ.circleArea(rad);
        assertEquals(actual, c);
    }

    @Test(description = "Zero scenary of the area calculation",
            dataProvider = "zeroDataForCircleArea")
    public void testZeroAreaCalc(int rad,  double c) {
        double actual = circ.circleArea(rad);
        assertEquals(actual, c);
    }

    @Test(description = "Zero scenary of the circumference calculation",
            dataProvider = "zeroDataForCircumference")
    public void testZeroCircumferenceCalc(int rad,  double c) {
        double actual = circ.circleArea(rad);
        assertEquals(actual, c);
    }

}
