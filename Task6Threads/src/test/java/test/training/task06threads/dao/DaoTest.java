package test.training.task06threads.dao;

import by.training.task06threads.dao.FileOperationImpl;
import by.training.task06threads.dao.exception.DaoException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DaoTest {
    FileOperationImpl fileOperation = new FileOperationImpl();

    @DataProvider(name = "good scenario for reading file")
    public Object[][] readingScenario() {
        return new Object[][]{
                {"F:\\Tasks\\Task6Threads\\src\\test\\resources\\matrixData1.txt", 273},
                {"F:\\Tasks\\Task6Threads\\src\\test\\resources\\matrixData2.txt", 19},
                {"F:\\Tasks\\Task6Threads\\src\\test\\resources\\matrixData3(incorrect).txt", 108},
                {"F:\\Tasks\\Task6Threads\\src\\test\\resources\\threadData1.txt", 6},
                {"F:\\Tasks\\Task6Threads\\src\\test\\resources\\threadData2.txt", 11},
                {"F:\\Tasks\\Task6Threads\\src\\test\\resources\\threadData3.txt", 11},
        };
    }

    @Test(description = "testing good scenario without exception", dataProvider = "good scenario for reading file")
    public void goodReadingTest(String filename, int result) throws DaoException {
        assertEquals(fileOperation.readFile(filename).length(), result);
    }

    @DataProvider(name = "bad scenario for reading file")
    public Object[][] readingBadScenario() {
        return new Object[][]{
                {"F:\\Tasks\\Task6Threads\\src\\test\\resources\\incorrectData.txt"},
                {"F:\\Tasks\\Task6Threads\\src\\test\\resources\\incorrectData1.txt"},
                {"F:\\Tasks\\Task6Threads\\src\\test\\resources\\incorrectData2.txt"},
                {"F:\\Tasks\\Task6Threads\\src\\test\\resources\\incorrectData3.txt"},
        };
    }

    @Test(description = "testing good scenario without exception", dataProvider = "bad scenario for reading file",
            expectedExceptions = {DaoException.class})
    public void badReadingTest(String filename) throws DaoException {
        assertEquals(fileOperation.readFile(filename),0);
    }
}
