package by.training.test.service;

import by.training.finaltask.bean.entities.Dean;
import by.training.finaltask.dao.connectionpool.ConnectionPool;
import by.training.finaltask.dao.exception.DaoException;
import by.training.finaltask.service.DeanService;
import by.training.finaltask.service.ServiceFactory;
import by.training.finaltask.service.excpetion.ServiceException;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

public class DeanTest {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    DeanService deanService;

    @BeforeSuite
    public void initConnection() throws DaoException {
        connectionPool.init("src/test/resources/database.properties");
    }

    @BeforeTest
    public void before() throws ServiceException {
        deanService = ServiceFactory.getInstance().getDeanService();
    }

    @AfterMethod
    public void afterMethod() throws ServiceException {
        deanService = ServiceFactory.getInstance().getDeanService();
    }

    @Test
    public void viewUniversities() throws ServiceException {
        assertEquals(deanService.findUniversities().size(), 4);
    }

    @Test
    public void viewDeans() throws ServiceException {
        assertEquals(deanService.viewDeans().size(), 10);
    }

    @DataProvider(name = "dataForFindDean")
    public Object[][] dataForFindDean() {
        return new Object[][]{
                {1, new Dean(1, "IEF", "Centralnaya10-214", 375292223001L, 4)},
                {2, new Dean(2, "EF", "Platnov37-131", 375773600222L, 1)},
                {null, null}
        };
    }

    @Test(description = "find id test", dataProvider = "dataForFindDean")
    public void findDeanById(Integer id, Dean result) throws ServiceException {
        assertEquals(deanService.findDean(id), result);
    }

    @DataProvider(name = "create data")
    public Object[][] createData() {
        return new Object[][]{
                {new Dean("address1","Faculty" , 375773600222L, 2), true},
                {new Dean("address2","Faculty" , 375L, 2), false},
                {new Dean("address4", "Faculty", 3211214414L, 1), false},
                {new Dean(null, "address5", 37521312311L, 2), false},
                {new Dean("faculty6", null, 375231341243L, 3), false}
        };
    }

    @Test(description = "create dean test", dataProvider = "create data")
    public void createTest(Dean dean, boolean result) throws ServiceException {
        assertEquals(deanService.create(dean), result);
    }

    @DataProvider(name = "updateData")
    public Object[][] updateData(){
        return new Object[][]{
                {new Dean(6, "KSISa", "Neponyat-213", 375297000221L, 3), true},
                {new Dean(null, "KSISa", "Neponyat-213", 375297000221L, 3), false},
                {new Dean(6, null, "Neponyat-213", 375297000221L, 3), false},
                {new Dean(6, "KSIS", null, 375297000221L, 3), false},
                {new Dean(6, "KSIS", "Neponyat-213", 375221L, 3), false},
                {new Dean(6, "KSIS", "Neponyat-213", 375297000221L, null), false},
                {new Dean(1, "KSISa", "Neponyat-213as", 375297002121L, 1), true},
        };
    }

    @Test(description = "updateTest", dataProvider = "updateData")
    public void updateTest(Dean dean, boolean result) throws ServiceException{
        assertEquals(deanService.updateDean(dean), result);
    }


    @DataProvider(name = "deleteDeanData")
    public Object[][] deleteDeanData(){
        return new Object[][]{
                {1, true},
                {1, false},
                {null, false},
                {312, false}
        };
    }

    @Test(description = "delete test", dataProvider = "deleteDeanData")
    public void deleteTest(Integer id, boolean result) throws ServiceException {
        assertEquals(deanService.deleteDean(id), result);
    }

}
