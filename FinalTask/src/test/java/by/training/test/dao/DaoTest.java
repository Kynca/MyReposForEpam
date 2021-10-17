package by.training.test.dao;

import by.training.finaltask.dao.connectionpool.ConnectionPool;
import by.training.finaltask.dao.exception.DaoException;
import by.training.finaltask.service.ServiceFactory;
import by.training.finaltask.service.StudentService;
import by.training.finaltask.service.UserService;
import by.training.finaltask.service.excpetion.ServiceException;
import by.training.finaltask.service.impl.UserServiceImpl;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class DaoTest {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Test(description = "test")
    public void testStudentService() throws ServiceException, DaoException {
        connectionPool.init("src/main/resources/database.properties");
        StudentService studentService = serviceFactory.getStudentService();
        assertEquals(studentService.deleteStudent(20),true);
    }

    @Test(description = "test")
    public void testUserService() throws ServiceException, DaoException {
        connectionPool.init("src/main/resources/database.properties");
        UserService userService = serviceFactory.getUserService();
        assertEquals(userService.deleteUser(2),true);
    }
}
