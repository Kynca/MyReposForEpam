package by.training.finaltask.dao;

import by.training.finaltask.bean.entities.User;
import by.training.finaltask.dao.exception.DaoException;

public interface UserDao extends Dao<Integer, User> {
    User findByLoginPass(String login,String pass) throws DaoException;
    Integer createUser(User user) throws DaoException;
    User findByLogin(String login) throws DaoException;
}
