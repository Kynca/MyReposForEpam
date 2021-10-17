package by.training.finaltask.service.transaction;

import by.training.finaltask.dao.daoimpl.BaseDao;
import by.training.finaltask.dao.exception.DaoException;
import by.training.finaltask.service.excpetion.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;

public class Transaction {
    private Connection connection;

    public Transaction(Connection connection) {
        this.connection = connection;
    }

    public void init(BaseDao... daos) {
        for (BaseDao dao : daos) {
            dao.setConnection(connection);
        }
    }

    public void endTransaction() throws ServiceException {
        try {
            if (connection != null) {
                connection.commit();
                connection.setAutoCommit(true);
                connection.close();
            }
        } catch (SQLException throwables) {
            throw new ServiceException(throwables);
        }
    }

    public void commit() throws ServiceException{
        try {
            connection.commit();
        } catch (SQLException throwables) {
            throw new ServiceException(throwables);
        }
    }

    public void rollback() throws ServiceException{
        try {
            connection.rollback();
        } catch (SQLException throwables) {
            throw new ServiceException(throwables);
        }
    }
}