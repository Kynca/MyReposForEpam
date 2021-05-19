package test.timetransformtesting.action;

import by.training.task01.ex22.TimeTransformation;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TimeTransformationTesting {
    TimeTransformation tt = new TimeTransformation();

    public TimeTransformationTesting() {
    }

    @DataProvider(
            name = "positiveDataForTimeTransformation"
    )
    public Object[][] createPositiveDataForTimeTransformation() {
        return new Object[][]{{60, "Time is: 0:1:0"}, {36001, "Time is: 10:0:1"}, {1, "Time is: 0:0:1"}};
    }

    @DataProvider(
            name = "negativeDataForTimeTransformation"
    )
    public Object[][] createNegativeDataForTimeTransformation() {
        return new Object[][]{{-1, "Time cannot be negative"}, {-2010, "Time cannot be negative"}};
    }

    @DataProvider(
            name = "zeroDataForTimeTransformation"
    )
    public Object[][] createZeroDataForTimeTransformation() {
        return new Object[][]{{0, "Time is: 0:0:0"}};
    }

    @Test(
            description = "Positive amount of seconds",
            dataProvider = "positiveDataForTimeTransformation"
    )
    public void testPositiveTimeTransformation(int seconds, String c) {
        String actual = this.tt.timeCalculation(seconds);
        Assert.assertEquals(actual, c);
    }

    @Test(
            description = "Negative amount of seconds",
            dataProvider = "negativeDataForTimeTransformation"
    )
    public void testNegativeTimeTransformation(int seconds, String c) {
        String actual = this.tt.timeCalculation(seconds);
        Assert.assertEquals(actual, c);
    }

    @Test(
            description = "Positive amount of seconds",
            dataProvider = "zeroDataForTimeTransformation"
    )
    public void testZeroTimeTransformation(int seconds, String c) {
        String actual = this.tt.timeCalculation(seconds);
        Assert.assertEquals(actual, c);
    }
}

