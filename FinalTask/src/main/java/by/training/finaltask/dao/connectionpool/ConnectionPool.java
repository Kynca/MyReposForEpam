package by.training.finaltask.dao.connectionpool;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

import by.training.finaltask.dao.daoimpl.DeanDaoImpl;
import by.training.finaltask.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

final public class ConnectionPool {
    private static final Logger daoLog = LogManager.getLogger(ConnectionPool.class.getName());


    private final static ReentrantLock locker = new ReentrantLock();
    private final static Properties properties = new Properties();
    private int maxSize = 50;
    private int checkConnectionTimeout = 60000;


    private BlockingQueue<PooledConnection> freeConnections = new LinkedBlockingQueue<>();
    private Set<PooledConnection> usedConnections = new ConcurrentSkipListSet<>();

    private ConnectionPool() {
    }

    public Connection getConnection() throws DaoException {
        locker.lock();
        PooledConnection connection = null;
        while (connection == null) {
            try {
                if (!freeConnections.isEmpty()) {
                    connection = freeConnections.take();
                    if (!connection.isValid(checkConnectionTimeout)) {
                        try {
                            connection.getConnection().close();
                        } catch (SQLException e) {
                        }
                        connection = null;
                    }
                } else if (usedConnections.size() < maxSize) {
                    connection = createConnection();
                    usedConnections.add(connection);
                    daoLog.info(String.format("Connection was received from pool. Current pool size: %d used connections; %d free connection", usedConnections.size(), freeConnections.size()));
                } else {
                    throw new DaoException("The limit of number of database connections is exceeded");
                }
            } catch (InterruptedException | SQLException e) {
                throw new DaoException("It is impossible to connect to a database", e);
            } finally {
                locker.unlock();
            }
        }
        return connection;
    }

    public void freeConnection(PooledConnection connection) {
        locker.lock();
        try {
            if (connection.isValid(checkConnectionTimeout)) {
                connection.clearWarnings();
                connection.setAutoCommit(true);
                usedConnections.remove(connection);
                freeConnections.put(connection);
            }
        } catch (SQLException | InterruptedException e1) {
            try {
                connection.getConnection().close();
            } catch (SQLException e2) {}
        } finally {
            locker.unlock();
        }
    }

    public void init(String dbProperties) throws DaoException {
        locker.lock();
        try {
            destroy();
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream(dbProperties);
            properties.load(input);
            Class.forName(properties.getProperty("db.driver"));
            for (int counter = 0; counter < Integer.parseInt(properties.getProperty("poolsize")); counter++) {
                freeConnections.put(createConnection());
            }
        } catch (ClassNotFoundException | SQLException | InterruptedException e) {
            throw new DaoException("It is impossible to initialize connection pool", e);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

    private static ConnectionPool instance = new ConnectionPool();

    public static ConnectionPool getInstance() {
        return instance;
    }

    private PooledConnection createConnection() throws SQLException {
        return new PooledConnection(DriverManager.getConnection(properties.getProperty("db.url"), properties));
    }

    public void destroy() {
        locker.lock();
        usedConnections.addAll(freeConnections);
        freeConnections.clear();
        for (PooledConnection connection : usedConnections) {
            try {
                connection.getConnection().close();
            } catch (SQLException e) {
            }
        }
        usedConnections.clear();
        locker.unlock();
    }

}
