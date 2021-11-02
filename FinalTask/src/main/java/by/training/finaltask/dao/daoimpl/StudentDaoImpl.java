package by.training.finaltask.dao.daoimpl;

import by.training.finaltask.bean.entities.Student;
import by.training.finaltask.dao.StudentDao;
import by.training.finaltask.dao.exception.DaoException;
import by.training.finaltask.service.excpetion.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl extends BaseDao implements StudentDao {

    private static final Logger daoLog = LogManager.getLogger(StudentDao.class.getName());

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
    private static final String IS_UNIQUE_MAIL = "SELECT user_id FROM student WHERE mail = ?";

    @Override
    public List<Student> findAll() throws DaoException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            List<Student> students = new ArrayList<>();
            resultSet = statement.executeQuery(SELECT);
            while (resultSet.next()) {
                students.add(setStudent(resultSet));
            }
            return students;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException | NullPointerException throwables) {
            }
        }
    }

    @Override
    public Student findById(Integer id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setInt(1, id);
            Student student = null;
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                student = setStudent(resultSet);
            }
            return student;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException | NullPointerException throwables) {
            }
        }
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE);
            statement.setInt(1, id);
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException throwables) {
            }
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
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException throwables) {
            }
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
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException throwables) {
            }
        }
    }

    @Override
    public List<Student> findDeanStudent(Integer deanId) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_BY_DEAN);
            statement.setInt(1, deanId);
            resultSet = statement.executeQuery();
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                students.add(setStudent(resultSet));
            }
            return students;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException | NullPointerException throwables) {
            }
        }
    }

    @Override
    public boolean isUniqueMail(String mail,Integer id) throws ServiceException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = connection.prepareStatement(IS_UNIQUE_MAIL);
            statement.setString(1, mail);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                if(id == null || id.equals(resultSet.getInt("user_id"))){
                    return true;
                }else {
                    return false;
                }


            }else {
                return true;
            }
        } catch (SQLException throwables) {
            throw new ServiceException(throwables);
        }finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException | NullPointerException throwables) {
            }
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
        daoLog.info(student + " added");
        return student;
    }
}
