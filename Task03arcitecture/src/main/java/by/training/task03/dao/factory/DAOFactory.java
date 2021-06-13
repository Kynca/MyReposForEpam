package by.training.task03.dao.factory;


import by.training.task03.dao.ArrayDAO;
import by.training.task03.dao.impl.FileArrayDAO;

public final class DAOFactory {
private static final DAOFactory instance= new DAOFactory();
private static ArrayDAO arrayDAOImpl=new FileArrayDAO();
private DAOFactory(){}

    public static DAOFactory getInstance() {
        return instance;
    }

    public static ArrayDAO getArrayDAO() {
        return arrayDAOImpl;
    }
}
