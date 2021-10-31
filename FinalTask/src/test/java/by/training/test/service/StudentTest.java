package by.training.test.service;

import by.training.finaltask.bean.entities.Student;
import by.training.finaltask.bean.entities.User;
import by.training.finaltask.dao.connectionpool.ConnectionPool;
import by.training.finaltask.dao.exception.DaoException;
import by.training.finaltask.service.ServiceFactory;
import by.training.finaltask.service.StudentService;
import by.training.finaltask.service.excpetion.ServiceException;
import org.testng.annotations.*;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class StudentTest {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    StudentService studentService;

    @BeforeSuite
    public void initConnection() throws DaoException {
        connectionPool.init("src/test/resources/database.properties");
    }

    @BeforeTest
    public void before() throws ServiceException {
        studentService = ServiceFactory.getInstance().getStudentService();
    }

    @AfterMethod
    public void afterMethod() throws ServiceException {
        studentService = ServiceFactory.getInstance().getStudentService();
    }

    @DataProvider(name = "data dean's students")
    public Object[][] findIdData(){
        return new Object[][]{
                {1,2},
                {2,2},
                {3,3},
                {4,3},
                {5,3},
                {6,1},
                {7,1},
                {8,2},
                {9,1},
                {10,1},
                {11,0},
        };
    }

    @Test(description = "test viewing students by dean_id", dataProvider = "data dean's students")
    public void viewStudents(Integer id, int size) throws ServiceException {
        List<Student> students = studentService.viewDeanStudents(id);
        assertEquals(students.size(), size);
    }

    @DataProvider(name = "data for findStudent")
    public Object[][] findStudentData(){
        return new Object[][]{
                {1, null},
                {2, new Student(2,"Matwey", "Gricenko", "Vladimirovich", "2002-03-29",
                        "matwey@mail.ru", 1)},
                {8, null},
                {null, null}
        };
    }

    @Test(description = "test finding student", dataProvider = "data for findStudent")
    public void findStudentTest(Integer id, Student result) throws ServiceException {
        assertEquals(studentService.viewInfo(id, false), result);
    }

    @DataProvider(name = "update info data")
    public Object[][] updateData(){
        return new Object[][]{
                {new Student(1,"sad","das","sda","2000-11-11","mail132@mail.ru",4), false},
                {new Student(2,"Matwey","das","sda","2000-11-11","myMail@gmail.com",4), true},
                {new Student(null,"Matwey","das","sda","2000-11-11"," mail132@mail",4), false},
                {new Student(2,null,"das","sda","2000-11-11"," mail132@mail",4), false},
                {new Student(2,"asd",null,"sda","2000-11-11"," mail132@mail",4), false},
                {new Student(2,"asd","ads",null,"2000-11-11"," mail132@mail",4), false},
                {new Student(2,"asd","ads","null","200-11-11"," mail132@mail",4), false},
                {new Student(2,"asd","ads","null","2000-11-11"," mail132mail",4), false},
                {new Student(2,"asd","ads","null","2000-11-11"," mail132mail",20), false},
                {new Student(2,"asd","ads","null","2000-11-11"," mail132mail",null), false},
                {new Student(100,"asd","ads","null","2000-11-11"," mail132mail",null), false},
        };
    }

    @Test(description = "test update student", dataProvider = "update info data")
    public void updateStudentTest(Student student, boolean result) throws ServiceException {
        assertEquals(studentService.updateInfo(student, false), result);
    }

    @DataProvider(name =  "delete data")
    public  Object[][] deleteData(){
        return new Object[][]{
                {1, false},
                {2, true},
                {3, true},
                {8, true},
                {null, false},
                {2, false},
        };
    }

    @Test(description = " delete student test", dataProvider = "delete data")
    public void deleteTest(Integer id, boolean result) throws ServiceException{
        assertEquals(studentService.deleteStudent(id, null), result);
    }
}
