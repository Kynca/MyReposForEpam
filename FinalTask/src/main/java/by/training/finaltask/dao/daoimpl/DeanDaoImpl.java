package by.training.finaltask.dao.daoimpl;

import by.training.finaltask.bean.entities.Dean;
import by.training.finaltask.dao.exception.DaoException;
import by.training.finaltask.dao.DeanDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeanDaoImpl extends BaseDao implements DeanDao {
    private static final Logger daoLog = LogManager.getLogger(DeanDaoImpl.class.getName());

    private static final String SELECT = "SELECT dean.id, faculty, address, phone_number, university_id, name " +
            "FROM dean INNER JOIN university u on dean.university_id = u.id";
    private static final String SELECT_BY_ID = "SELECT faculty, address, phone_number, university_id, name " +
            "FROM dean INNER JOIN university u on dean.university_id = u.id WHERE dean.id = ? ";
    private static final String CREATE = "INSERT INTO dean(address, faculty, phone_number, university_id) VALUES (?,?,?,?)";
    private static final String DELETE = "DELETE FROM dean WHERE id = ?";
    private static final String UPDATE = "UPDATE dean SET address = ?, faculty = ?, phone_number = ?, university_id = ? WHERE id = ?";

    @Override
    public List<Dean> findAll() throws DaoException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            List<Dean> deans = new ArrayList<>();
            resultSet = statement.executeQuery(SELECT);
            while (resultSet.next()) {
                Dean dean = new Dean();
                dean.setId(resultSet.getInt("id"));
                dean.setAddress(resultSet.getString("address"));
                dean.setFaculty(resultSet.getString("faculty"));
                dean.setPhoneNumber(resultSet.getLong("phone_number"));
                dean.setUniversityId(resultSet.getInt("university_id"));
                dean.setUniversityName(resultSet.getString("name"));
                daoLog.info(dean + " added to list");
                deans.add(dean);
            }
            return deans;
        } catch (SQLException e) {
            throw new DaoException(e);
        }finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException | NullPointerException throwables) { }
        }
    }

    @Override
    public Dean findById(Integer id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Dean dean = null;
            if (resultSet.next()) {
                dean = new Dean();
                dean.setId(id);
                dean.setAddress(resultSet.getString("address"));
                dean.setFaculty(resultSet.getString("faculty"));
                dean.setPhoneNumber(resultSet.getLong("phone_number"));
                dean.setUniversityId(resultSet.getInt("university_id"));
                dean.setUniversityName(resultSet.getString("name"));
                daoLog.info(dean + " found and returned");
            }
            return dean;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException | NullPointerException throwables) { }
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
        }finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException throwables) { }
        }
    }

    @Override
    public boolean create(Dean dean) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CREATE);
            statement.setString(1, dean.getAddress());
            statement.setString(2, dean.getFaculty());
            statement.setLong(3, dean.getPhoneNumber());
            statement.setInt(4, dean.getUniversityId());
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException throwables) { }
        }
    }

    @Override
    public boolean update(Dean dean) throws DaoException {
        PreparedStatement statement = null;
        daoLog.debug(dean);
        try {
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, dean.getAddress());
            statement.setString(2, dean.getFaculty());
            statement.setLong(3, dean.getPhoneNumber());
            statement.setInt(4, dean.getUniversityId());
            statement.setInt(5, dean.getId());
            daoLog.debug(statement.toString());
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException throwables) { }
        }
    }


}
