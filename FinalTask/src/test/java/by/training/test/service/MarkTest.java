package by.training.test.service;

import by.training.finaltask.bean.entities.Mark;
import by.training.finaltask.dao.connectionpool.ConnectionPool;
import by.training.finaltask.dao.exception.DaoException;
import by.training.finaltask.service.MarkService;
import by.training.finaltask.service.ServiceFactory;
import by.training.finaltask.service.StudentService;
import by.training.finaltask.service.UserService;
import by.training.finaltask.service.excpetion.ServiceException;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import java.util.List;

public class MarkTest {
    MarkService markService;
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    @BeforeTest()
    public void init() {
        try {
            connectionPool.init("database.properties");
            markService = ServiceFactory.getInstance().getMarkService();
        } catch (DaoException | ServiceException e) {
            e.printStackTrace();
        }
    }

    @DataProvider(name = "data for markService")
    public Object[][] dataForSelect() {
        return new Object[][]{
                {2, 3},
                {1, 0},
                {3, 2}
        };
    }

    @Test(testName = "selectTest", dataProvider = "data for markService")
    public void selectTest(int id, int result) throws ServiceException {
        List<Mark> markList = markService.viewMarks(id);
        assertEquals(markList.size(), result);
    }

    @AfterMethod
    public void afterMethod() throws ServiceException {
        markService = ServiceFactory.getInstance().getMarkService();
    }
}
