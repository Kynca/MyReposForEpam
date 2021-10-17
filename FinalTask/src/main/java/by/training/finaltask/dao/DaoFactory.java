package by.training.finaltask.dao;

import by.training.finaltask.dao.connectionpool.ConnectionPool;
import by.training.finaltask.dao.daoimpl.*;
import by.training.finaltask.dao.exception.DaoException;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();

    private DeanDaoImpl deanDao = new DeanDaoImpl();
    private UserDaoImpl userDao = new UserDaoImpl();
    private StudentDaoImpl studentDao = new StudentDaoImpl();
    private MarkDaoImpl markDao = new MarkDaoImpl();

    private UniversityDaoImpl universityDao = new UniversityDaoImpl();
    private DocumentDaoImpl documentDao = new DocumentDaoImpl();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return instance;
    }

    public DeanDaoImpl getDeanDao() {
        return deanDao;
    }

    public UserDaoImpl getUserDao() {
        return userDao;
    }

    public StudentDaoImpl getStudentDao() {
        return studentDao;
    }

    public MarkDaoImpl getMarkDao() {
        return markDao;
    }

    public UniversityDaoImpl getUniversityDao() {
        return universityDao;
    }

    public DocumentDaoImpl getDocumentDao() {
        return documentDao;
    }

}
