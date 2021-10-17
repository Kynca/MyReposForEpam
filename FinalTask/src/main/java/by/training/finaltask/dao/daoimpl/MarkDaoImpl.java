package by.training.finaltask.dao.daoimpl;

import by.training.finaltask.bean.entities.Mark;
import by.training.finaltask.dao.MarkDao;
import by.training.finaltask.dao.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarkDaoImpl extends BaseDao implements MarkDao {

    private static final String FIND_BY_STUDENT_ID = "SELECT name, rate, issue_date, student_id  FROM marks " +
            "INNER JOIN subject s on marks.subject_id = s.id";
    private static final String FIND_SUBJECT = "SELECT id FROM subject WHERE name = ?";
    private static final String INSERT = "INSERT INTO marks(subject_id, rate, issue_date, student_id) VALUES (?,?,?,?) ";
    private static final String UPDATE = "UPDATE marks SET rate = ? WHERE subject_id = ? AND student_id = ?";

    @Override
    public List<Mark> findAll() throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Mark findById(Integer id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(Mark entity) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT);
            return statement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw  new DaoException(throwables);
        }
    }

    @Override
    public boolean update(Mark entity) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setDouble(1, entity.getRate());
            statement.setInt(2, findSubject(entity.getSubjectName()));
            statement.setInt(3, entity.getStudentId());
            return statement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public List<Mark> findByStudentId(Integer id) throws DaoException{
        List<Mark> marks = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_STUDENT_ID);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                String name = rs.getString("name");
                Double rate = rs.getDouble("rate");
                String date = String.valueOf(rs.getDate("issue_date"));
                Integer studentId = rs.getInt("student_id");
                Mark mark = new Mark(name, rate, date, studentId);
                marks.add(mark);
            }
            return marks;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public Integer findSubject(String name) throws DaoException{
        Integer result = null;
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_SUBJECT);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
               result = rs.getInt("id");
            }
            return result;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }


}
