package by.training.finaltask.dao.daoimpl;

import by.training.finaltask.bean.entities.Student;
import by.training.finaltask.dao.StudentDao;
import by.training.finaltask.dao.connectionpool.ConnectionPool;
import by.training.finaltask.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl extends BaseDao implements StudentDao {

    private final Logger debugLog = LogManager.getLogger("DebugLog");

    private static final String SELECT = "SELECT user_id, name, lastname, patronymic, birthdate, mail, dean_id " +
            "FROM student INNER JOIN program_user u ON student.user_id = u.id WHERE dean_id IS NOT NULL AND role NOT LIKE 0 ORDER BY user_id";
    private static final String SELECT_BY_ID = "SELECT user_id, name, lastname, patronymic, birthdate, mail, dean_id " +
            "FROM student WHERE user_id = ?";
    private static final String SELECT_BY_DEAN = "SELECT user_id, name, lastname, patronymic, birthdate, mail, dean_id " +
            "FROM student WHERE dean_id = ?";
    private static final String DELETE = "DELETE FROM student WHERE user_id LIKE ?";
    private static final String CREATE = "INSERT INTO student(user_id, name, lastname, patronymic, birthdate, mail, dean_id)" +
            "VALUES (?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE student SET name = ?,lastname = ?, patronymic =?, mail = ?,dean_id =? "
            + "WHERE user_id = ?";

    @Override
    public List<Student> findAll() throws DaoException {
        Statement statement;
        try {
            statement = connection.createStatement();
            debugLog.debug("created connection");
            List<Student> students = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(SELECT);
            debugLog.debug("created result set");
            while (resultSet.next()) {
                students.add(setStudent(resultSet));
            }
            return students;
        } catch (SQLException e) {
            debugLog.debug(e.getMessage() + e);
            throw new DaoException(e);
        }
    }

    @Override
    public Student findById(Integer id) throws DaoException {
        PreparedStatement statement;
        debugLog.debug(id);
        try {
            statement = connection.prepareStatement(SELECT_BY_ID);
            debugLog.debug("prepared statement");
            statement.setInt(1, id);
            debugLog.debug("seted id");
            Student student = null;
            ResultSet resultSet = statement.executeQuery();
            debugLog.debug("prepared result set");
            if (resultSet.next()) {
                student = setStudent(resultSet);
            }
            return student;
        } catch (SQLException e) {
            debugLog.debug("cannot find user" + e);
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        PreparedStatement statement;
        debugLog.debug("in delete student = " + id);
        try {
            statement = connection.prepareStatement(DELETE);
            statement.setInt(1, id);
            debugLog.debug("in the end of student delete");
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            debugLog.debug(e + e.getMessage() + "exception in sql");
            throw new DaoException(e);
        }
    }

    @Override
    public boolean create(Student student) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CREATE);
            statement.setInt(1, student.getId());
            statement.setString(2, student.getName());
            statement.setString(3, student.getLastname());
            statement.setString(4, student.getPatronymic());
            statement.setDate(5, Date.valueOf(student.getDate()));
            statement.setString(6, student.getMail());
            statement.setInt(7, student.getDeanId());
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean update(Student student) throws DaoException {
        int id = student.getId();
        String name = student.getName();
        String lastname = student.getLastname();
        String patronymic = student.getPatronymic();
        String mail = student.getMail();
        int deanId = student.getDeanId();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, name);
            statement.setString(2, lastname);
            statement.setString(3, patronymic);
            statement.setString(4, mail);
            statement.setInt(5, deanId);
            statement.setInt(6, id);
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Student> findDeanStudent(Integer deanId) throws DaoException {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(SELECT_BY_DEAN);
            ResultSet resultSet = statement.executeQuery();
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                setStudent(resultSet);
            }
            return students;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    private Student setStudent(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getInt("user_id"));
        student.setName(resultSet.getString("name"));
        student.setLastname(resultSet.getString("lastname"));
        student.setPatronymic(resultSet.getString("patronymic"));
        student.setDate(String.valueOf(resultSet.getDate("birthdate")));
        student.setMail(resultSet.getString("mail"));
        student.setDeanId(resultSet.getInt("dean_id"));
        return student;
    }
}
