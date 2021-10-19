package by.training.finaltask.dao.daoimpl;

import by.training.finaltask.bean.entities.Role;
import by.training.finaltask.bean.entities.User;
import by.training.finaltask.dao.UserDao;
import by.training.finaltask.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
    private static final Logger daoLog = LogManager.getLogger(UserDaoImpl.class.getName());

    private static final String SELECT = "SELECT id, login, role FROM program_user WHERE role NOT LIKE 0 ORDER BY id";
    private static final String SELECT_BY_ID = "SELECT id, login, role FROM program_user WHERE id = ?";
    private static final String SELECT_LOGIN = "SELECT id, login FROM program_user WHERE login = ?";
    private static final String SELECT_LOGIN_PASS = "SELECT id, login, role FROM program_user WHERE login = ? AND password = ?";
    private static final String CREATE = "INSERT INTO program_user(login, password, role) VALUES (? , ?, ?)";
    private static final String DELETE = "DELETE FROM program_user WHERE id LIKE ?";
    private static final String UPDATE = "UPDATE program_user SET login = ?, role = ? WHERE id = ?";

    @Override
    public List<User> findAll() throws DaoException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            List<User> users = new ArrayList<>();
            resultSet = statement.executeQuery(SELECT);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setRole(Role.getByCode(resultSet.getInt("role")));
                users.add(user);
            }
            return users;
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
    public User findById(Integer id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setRole(Role.getByCode(resultSet.getInt("role")));
                return user;
            }
            return null;
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
    public boolean create(User user) throws DaoException {
        daoLog.info(user + " to create");
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CREATE);
            statement.setString(1, user.getLogin());
            statement.setInt(2, user.getRole().getValue());
            statement.setInt(3, user.getRole().getValue());
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
    public boolean update(User user) throws DaoException {
        PreparedStatement statement = null;
        try {
            User user1 = findByLogin(user.getLogin());
            if (user1 == null || !user1.getLogin().equals(user.getLogin()) || user1.getId().equals(user.getId())) {
                statement = connection.prepareStatement(UPDATE);
                statement.setString(1, user.getLogin());
                statement.setInt(2, user.getRole().getValue());
                statement.setInt(3, user.getId());
                return statement.executeUpdate() > 0;
            } else {
                return false;
            }
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
    public User findByLoginPass(String login, String pass) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            statement = connection.prepareStatement(SELECT_LOGIN_PASS);
            statement.setString(1, login);
            statement.setString(2, pass);
            User user = null;
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setRole(Role.getByCode(resultSet.getInt("role")));
                user.setLogin(login);
            }
            return user;
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
    public Integer createGeneratedUser(String name, String lastname) throws DaoException {
        String randomGeneratedLogin = name + lastname;

        if (findByLogin(randomGeneratedLogin) != null) {
            int k = 0;
            while (findByLogin(randomGeneratedLogin + k) == null) {
                k++;
            }
            randomGeneratedLogin += k;
        }
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(CREATE);
            statement.setString(1, randomGeneratedLogin);
            statement.setString(2, "randomPass");
            statement.setInt(3, Role.STUDENT.getValue());
            if (statement.executeUpdate() < 0) {
                return null;
            }
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
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

    private User findByLogin(String login) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setLogin(resultSet.getString("login"));
                user.setId(resultSet.getInt("id"));
            }
            return user;
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

}
