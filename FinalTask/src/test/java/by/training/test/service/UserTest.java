package by.training.test.service;

import by.training.finaltask.bean.entities.Role;
import by.training.finaltask.bean.entities.User;
import by.training.finaltask.dao.connectionpool.ConnectionPool;
import by.training.finaltask.dao.exception.DaoException;
import by.training.finaltask.service.ServiceFactory;
import by.training.finaltask.service.UserService;
import by.training.finaltask.service.excpetion.ServiceException;
import org.testng.annotations.*;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class UserTest {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    UserService userService;

    @BeforeSuite
    public void initConnection() throws DaoException {
        connectionPool.init("src/test/resources/database.properties");
    }

    @BeforeTest
    public void before() throws ServiceException {
        userService = ServiceFactory.getInstance().getUserService();
    }

    @AfterMethod
    public void afterMethod() throws ServiceException {
        userService = ServiceFactory.getInstance().getUserService();
    }

    @Test
    public void viewUserTest() throws ServiceException {
        List<User> users = userService.viewUsers();
        assertEquals(users.size(), 20);
    }

    @DataProvider(name = "find by id data")
    public Object[][] dataForFindId(){
        return new Object[][]{
                {1, new User(1,"user1", Role.ADMINISTRATOR)},
                {2, new User(2,"user2", Role.STUDENT)},
                {3, new User(3,"user3", Role.STUDENT)},
                {8, new User(8,"user8", Role.DEAN)},
                {21, new User(21,"user21", Role.STUDENT)},
                {45, null},
        };
    }

    @Test(description = "is correct finding", dataProvider = "find by id data")
    public void findByIdTTest(Integer id, User result) throws ServiceException{
        assertEquals(userService.findUser(id), result);
    }

    @DataProvider(name = "login data")
    public Object[][] dataLogin(){
        return new Object[][]{
                {new String[]{"user1", "password1"}, new User(1,"user1", Role.ADMINISTRATOR)},
                {new String[]{"user2", "password2"}, new User(2, "user2", Role.STUDENT)},
                {new String[]{"user9", "password9"}, new User(9, "user9", Role.DEAN)},
                {new String[]{null, "password2"}, null},
                {new String[]{null, null}, null},
                {new String[]{"user1", null}, null},
                {new String[]{"user1", "pass"}, null},
        };
    }

    @Test(description = "login test", dataProvider = "login data")
    public void loginTest(String[] data, User result) throws ServiceException {
        assertEquals(userService.login(data[0], data[1]), result);
    }

    @DataProvider(name = "data for update")
    public Object[][] updateData(){
        return new Object[][]{
                {new User(1,"user31",Role.ADMINISTRATOR), new User(1,"user1", Role.ADMINISTRATOR)},
                {new User(2,"user2",Role.STUDENT),new User(2,"user2",Role.STUDENT)},
                {new User(2,"user3",Role.STUDENT), new User(2,"user2",Role.STUDENT)},
                {new User(2,"logisadas",Role.STUDENT), new User(2,"logisadas",Role.STUDENT)},
                {new User(2,"logisadas",Role.DEAN), new User(2,"logisadas",Role.DEAN)},
                {new User(2,"logisadas",Role.ADMINISTRATOR), new User(2,"logisadas",Role.DEAN)},
                {new User(null,null, null), null},
                {new User(3,null, null), new User(3, "user3", Role.STUDENT)},
                {new User(3,"user1", null), new User(3, "user3", Role.STUDENT)},
                {new User(3,null, Role.ADMINISTRATOR), new User(3, "user3", Role.STUDENT)},
        };
    }

    @Test(description = "update user test", dataProvider ="data for update" )
    public void updateUserTest(User user, User result) throws ServiceException {
        userService.updateUser(user);
        userService = ServiceFactory.getInstance().getUserService();
        User user1 = userService.findUser(user.getId());
        assertEquals(user1, result);
    }

    @DataProvider(name = "delete data")
    public Object[][] deleteData(){
        return new Object[][]{
                {1, new User(1,"user1", Role.ADMINISTRATOR)},
                {2, null},
                {4, null},
                {8, null},
                {null, null}
        };
    }

    @Test(description = "delete user from database test", dataProvider = "delete data")
    public void deleteUserTest(Integer id, User result) throws ServiceException {
        userService.deleteUser(id);
        userService = ServiceFactory.getInstance().getUserService();
        User user = userService.findUser(id);
        assertEquals(user, result);
    }

}
