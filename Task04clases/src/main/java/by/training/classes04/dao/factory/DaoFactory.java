package by.training.classes04.dao.factory;

import by.training.classes04.dao.impl.DaoImpl;


/**
 * Singleton DAOFactory with classes which works with external environment
 */
public class DaoFactory {

    private static final DaoFactory instance=new DaoFactory();

    static DaoImpl dao = new DaoImpl();

    private DaoFactory(){}

    public static DaoFactory getInstance() {
        return instance;
    }

    public static DaoImpl getDaoImpl() {
        return dao;
    }
}
