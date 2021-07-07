package by.training.task05.dao;

public class DaoFactory {

    private static final DaoFactory instance = new DaoFactory();

    static DaoImpl daoImpl = new DaoImpl();

    public static DaoFactory getInstance() {
        return instance;
    }

    public static DaoImpl getDaoImpl() {
        return daoImpl;
    }

    private DaoFactory(){}
}
