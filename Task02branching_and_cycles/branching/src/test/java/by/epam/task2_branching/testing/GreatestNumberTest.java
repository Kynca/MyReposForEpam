package by.epam.task2_branching.testing;

import by.epam.task2_branching.GreatestNumber;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class GreatestNumberTest {
    GreatestNumber gn= new GreatestNumber();

    @DataProvider(name="possible scenarios")
    public Object[][] greatestNum(){
        return new Object[][]{
                {new int[]{1,2},2},
                {new int[]{1000,150000},150000},
                {new int[]{Integer.MAX_VALUE,3},Integer.MAX_VALUE},
                {new int[]{-10,2},2},
                {new int[]{2,2},2}
        };
    }

    @Test(description = "Check all possible scenarios",dataProvider = "possible scenarios")
    public void GreatNumCheck(int []nums,int result){
        assertEquals(gn.numberComparator(nums[0],nums[1]),result);
    }
}
