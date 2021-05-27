package by.epam.task2_branching.testing;

import by.epam.task2_branching.TriangleDefinition;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class TriangleTest {
    TriangleDefinition td= new TriangleDefinition();

    @DataProvider(name="right triangle")
    public Object[][] rightScenario(){
        return new Object[][]{
                {new int[]{90,12},"It is a right triangle"},
                {new int[]{78,12},"It is a right triangle"},
                {new int[]{50,90},"It is a right triangle"},
        };
    }

    @Test(description = "triangle is right",dataProvider = "right triangle")
    public void rightTriangle(int[] angles,String result){
        assertEquals(td.isRightTriangle(angles[0],angles[1]),result);
    }

    @DataProvider(name="not right triangle")
    public Object[][] notRightScenario(){
        return new Object[][]{
                {new int[]{91,78},"It isn't right triangle"},
                {new int[]{60,60},"It isn't right triangle"},
                {new int[]{120,30},"It isn't right triangle"},
        };
    }

    @Test(description = "triangle is not right",dataProvider = "not right triangle")
    public void notRightTriangle(int[] angles,String result){
        assertEquals(td.isRightTriangle(angles[0],angles[1]),result);
    }

    @DataProvider(name="not a triangle")
    public Object[][] notTriangleScenario(){
        return new Object[][]{
                {new int[]{91,128},"Triangle with 91 and 128 angles can not exist"},
                {new int[]{90,90},"Triangle with 90 and 90 angles can not exist"},
                {new int[]{100,128},"Triangle with 100 and 128 angles can not exist"},
        };
    }

    @Test(description = "triangle is not exist",dataProvider = "not a triangle")
    public void notTriangleScenario(int[] angles,String result){
        assertEquals(td.isRightTriangle(angles[0],angles[1]),result);
    }

    @DataProvider(name="incorrect data")
    public Object[][] notCorrectData(){
        return new Object[][]{
                {new int[]{-10,78},"Illegal data for angles"},
                {new int[]{0,0},"Illegal data for angles"},
                {new int[]{120,-20},"Illegal data for angles"},
                {new int[]{-120,-20},"Illegal data for angles"},
        };
    }

    @Test(description = "data isn't correct",dataProvider = "incorrect data",
            expectedExceptions = {IllegalArgumentException.class})
    public void notCorrectData(int[] angles,String result){
        assertEquals(td.isRightTriangle(angles[0],angles[1]),result);
    }
}
