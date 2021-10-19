package by.training.finaltask.service.transaction;

import by.training.finaltask.dao.connectionpool.ConnectionPool;
import by.training.finaltask.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFactory {
    private Connection connection;

    public TransactionFactory() throws DaoException {
        connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException throwables) {
            throw new DaoException("It is impossible to turn off autocommiting for database connection" ,throwables);
        }
    }

    public Transaction createTransaction(){
        return new Transaction(connection);
    }

    public void close() throws DaoException{
        try {
            connection.close();
        } catch (SQLException throwables) {
            throw new DaoException("impossible to close transaction" ,throwables);
        }
    }
}
