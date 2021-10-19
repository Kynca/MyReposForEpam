package by.training.finaltask.dao.daoimpl;

import by.training.finaltask.bean.entities.University;
import by.training.finaltask.dao.UniversityDao;
import by.training.finaltask.dao.connectionpool.ConnectionPool;
import by.training.finaltask.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UniversityDaoImpl extends BaseDao implements UniversityDao {
    private static final Logger daoLog = LogManager.getLogger(UniversityDaoImpl.class.getName());

    private static final String SELECT = "SELECT id, name FROM university";
    private static final String SELECT_UNIVERSITY = "SELECT id, name FROM university WHERE id = ?";

    @Override
    public List<University> findAll() throws DaoException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            List<University> universities = new ArrayList<>();
            resultSet = statement.executeQuery(SELECT);
            while (resultSet.next()) {
                University university = new University();
                university.setId(resultSet.getInt("id"));
                university.setName(resultSet.getString("name"));
                universities.add(university);
            }
            return universities;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (SQLException | NullPointerException throwables) {
            }
        }
    }

    @Override
    public University findById(Integer id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_UNIVERSITY);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                University university = new University();
                university.setId(id);
                university.setName(resultSet.getString("name"));
                return university;
            }
            return null;
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
    public boolean delete(Integer id) throws DaoException {
        return false;
    }

    @Override
    public boolean create(University university) throws DaoException {
        return false;
    }

    @Override
    public boolean update(University university) throws DaoException {
        return false;
    }
}
