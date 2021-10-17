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

import by.training.finaltask.dao.exception.DaoException;

final public class ConnectionPool {
    //    private static Logger logger = Logger.getLogger(ConnectionPool.class);

    private final static ReentrantLock locker = new ReentrantLock();
    private final static Properties properties = new Properties();
    private int maxSize = 50;
    private int checkConnectionTimeout = 600000;


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
//        logger.debug(String.format("Connection was received from pool. Current pool size: %d used connections; %d free connection", usedConnections.size(), freeConnections.size()));
                } else {
//                    logger.error("The limit of number of database connections is exceeded");
                    throw new DaoException();
                }
            } catch (InterruptedException | SQLException e) {
//                logger.error("It is impossible to connect to a database", e);
                throw new DaoException(e);
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
//                logger.debug(String.format("Connection was returned into pool. Current pool size: %d used connections; %d free connection", usedConnections.size(), freeConnections.size()));
            }
        } catch (SQLException | InterruptedException e1) {
//            logger.warn("It is impossible to return database connection into pool", e1);
            try {
                connection.getConnection().close();
            } catch (SQLException e2) {
            }
        } finally {
            locker.unlock();
        }
    }

    public void init(String dbProperties) throws DaoException {
        locker.lock();
        try {
            destroy();
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream("database.properties");
            properties.load(input);
            Class.forName(properties.getProperty("db.driver"));
            for (int counter = 0; counter < Integer.parseInt(properties.getProperty("poolsize")); counter++) {
                freeConnections.put(createConnection());
            }
        } catch (ClassNotFoundException | SQLException | InterruptedException e) {
//            logger.fatal("It is impossible to initialize connection pool", e);
            throw new DaoException(e);
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
        return new PooledConnection(DriverManager.getConnection(properties.getProperty("db.url"),properties));
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
