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
    private static final Logger debugLog = LogManager.getLogger("DebugLog");

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
        try {
            statement = connection.createStatement();
            List<Dean> deans = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(SELECT);
            debugLog.debug(resultSet.getMetaData().getColumnTypeName(1));
            debugLog.debug(resultSet.getMetaData().getColumnName(1));
            debugLog.debug(resultSet.getMetaData().getColumnTypeName(4));
            debugLog.debug(resultSet.getMetaData().getColumnName(4));
            while (resultSet.next()) {
                Dean dean = new Dean();
                dean.setId(resultSet.getInt("id"));
                dean.setAddress(resultSet.getString("address"));
                dean.setFaculty(resultSet.getString("faculty"));
                dean.setPhoneNumber(resultSet.getLong("phone_number"));
                dean.setUniversityId(resultSet.getInt("university_id"));
                dean.setUniversityName(resultSet.getString("name"));
                deans.add(dean);
            }
            return deans;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throw new DaoException("unable to close statement", throwables);
            }
        }
    }

    @Override
    public Dean findById(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Dean dean = null;
            if (resultSet.next()) {
                dean = new Dean();
                dean.setId(id);
                dean.setAddress(resultSet.getString("address"));
                dean.setFaculty(resultSet.getString("faculty"));
                dean.setPhoneNumber(resultSet.getLong("phone_number"));
                dean.setUniversityId(resultSet.getInt("university_id"));
                dean.setUniversityName(resultSet.getString("name"));
            }
            return dean;
        } catch (SQLException throwables) {
            debugLog.debug(throwables);
            throw new DaoException(throwables);
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
        }
    }

    @Override
    public boolean create(Dean dean) throws DaoException {
        debugLog.debug("in create");
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
        }
    }

    @Override
    public boolean update(Dean dean) throws DaoException {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, dean.getAddress());
            statement.setString(2, dean.getFaculty());
            statement.setLong(3, dean.getPhoneNumber());
            statement.setInt(4, dean.getUniversityId());
            statement.setInt(5, dean.getId());
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


}
