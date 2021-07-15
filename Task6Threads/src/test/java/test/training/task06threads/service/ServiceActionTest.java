package test.training.task06threads.service;

import by.training.task06threads.bean.MatrixStorage;
import by.training.task06threads.service.ServiceActionImpl;
import by.training.task06threads.service.exception.ServiceException;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class ServiceActionTest {

    ServiceActionImpl action = new ServiceActionImpl();
    MatrixStorage storage = MatrixStorage.getInstance();

    @DataProvider(name = "good scenario for matrix ini")
    public Object[][] goodMatrixData() {
        return new Object[][]{
                {new String[]{"F:\\Tasks\\Task6Threads\\src\\test\\resources\\matrixData1.txt", " "}, 10},
                {new String[]{"F:\\Tasks\\Task6Threads\\src\\test\\resources\\matrixData2.txt", " "}, 3},
        };
    }

    @Test(description = "testing good scenario for matrixIni in storage", dataProvider = "good scenario for matrix ini")
    public void goodMatrixScenarioTest(String[] data, int result) throws ServiceException {
        action.matrixInitialisation(data[0], data[1]);
        assertEquals(storage.getSize(), result);
    }

    @DataProvider(name = "bad scenario for matrix ini")
    public Object[][] badMatrixData() {
        return new Object[][]{
                {"F:\\Tasks\\Task6Threads\\src\\test\\resources\\matrixData3(incorrect).txt", " "},
                {"F:\\Tasks\\Task6Threads\\src\\test\\resources\\threadData1.txt", " "},
                {"F:\\Tasks\\Task6Threads\\src\\test\\resources\\threadData2.txt", " "},
                {"F:\\Tasks\\Task6Threads\\src\\test\\resources\\threadData3.txt", " "},
                {"F:\\Tasks\\Task6Threads\\src\\test\\resources\\incorrectData.txt", " "},
                {"F:\\Tasks\\Task6Threads\\src\\test\\resources\\incorrectData1.txt", " "},
                {"F:\\Tasks\\Task6Threads\\src\\test\\resources\\incorrectData2.txt", " "},
                {"F:\\Tasks\\Task6Threads\\src\\test\\resources\\incorrectData3.txt", " "}
        };
    }

    @Test(description = "testing bad scenario for matrixIni in storage", dataProvider = "bad scenario for matrix ini",
            expectedExceptions = {ServiceException.class}
    )
    public void badMatrixScenarioTest(String filename, String delimiter) throws ServiceException {
        action.matrixInitialisation(filename, delimiter);
    }

    @DataProvider(name = "good scenario for thread ini")
    public Object[][] goodThreadData() {
        return new Object[][]{
                {new String[]{"F:\\Tasks\\Task6Threads\\src\\test\\resources\\threadData1.txt", " "}, 2},
                {new String[]{"F:\\Tasks\\Task6Threads\\src\\test\\resources\\threadData2.txt", " "}, 5},
                {new String[]{"F:\\Tasks\\Task6Threads\\src\\test\\resources\\threadData3.txt", " "}, 4}
        };
    }

    @Test(description = "testing good scenario for threadIni in storage", dataProvider = "good scenario for thread ini")
    public void goodThreadIniTest(String[] data, int result) throws ServiceException {
        action.threadInitialisation(data[0], data[1]);
        assertEquals(storage.getNumberOfThreads(), result);
    }

    @DataProvider(name = "bad scenario for thread ini")
    public Object[][] badThreadData() {
        return new Object[][]{
                {"F:\\Tasks\\Task6Threads\\src\\test\\resources\\matrixData3(incorrect).txt", " "},
                {"F:\\Tasks\\Task6Threads\\src\\test\\resources\\incorrectData.txt", " "},
                {"F:\\Tasks\\Task6Threads\\src\\test\\resources\\incorrectData1.txt", " "},
                {"F:\\Tasks\\Task6Threads\\src\\test\\resources\\incorrectData2.txt", " "},
                {"F:\\Tasks\\Task6Threads\\src\\test\\resources\\incorrectData3.txt", " "},
                {"F:\\Tasks\\Task6Threads\\src\\test\\resources\\matrixData1.txt", " "},
                {"F:\\Tasks\\Task6Threads\\src\\test\\resources\\matrixData2.txt", " "},
        };
    }

    @Test(description = "testing bad scenario for threadIni in storage", dataProvider = "bad scenario for thread ini",
            expectedExceptions = {ServiceException.class})
    public void badThreadIniTest(String filename, String delimiter) throws ServiceException {
        action.threadInitialisation(filename, delimiter);
    }

    @BeforeClass
    void setStorage() throws ServiceException {
        action.matrixInitialisation("F:\\Tasks\\Task6Threads\\src\\test\\resources\\matrixData1.txt", " ");
        action.threadInitialisation("F:\\Tasks\\Task6Threads\\src\\test\\resources\\threadData2.txt", " ");
    }

    @Test(description = "Testing barrier fill", invocationCount = 100, invocationTimeOut = 100000)
    public void barrierTest() {
        action.cyclicBarrierFill();
        try {
            TimeUnit.MILLISECONDS.sleep(50);
            assertEquals(storage.getChanges(), 10);
            storage.reset();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(description = "Testing lock fill", invocationCount = 100, invocationTimeOut = 100000)
    public void lockTest() {
        action.lockFill();
        try {
            TimeUnit.MILLISECONDS.sleep(100);
            assertEquals(storage.getChanges(), 10);
            storage.reset();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(description = "Testing semaphore fill", invocationCount = 100, invocationTimeOut = 100000)
    public void semaphoreTest() {
        action.semaphoreFill();
        try {
            TimeUnit.MILLISECONDS.sleep(100);
            assertEquals(storage.getChanges(), 10);
            System.out.println();
            storage.reset();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(description = "Testing pool fill", invocationCount = 100, invocationTimeOut = 100000)
    public void poolTest() {
        try {
            action.poolFill();
            TimeUnit.MILLISECONDS.sleep(100);
            assertEquals(storage.getChanges(), 10);
            System.out.println();
            storage.reset();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
