package by.training.finaltask.service.impl;

import by.training.finaltask.bean.entities.Document;
import by.training.finaltask.bean.entities.Role;
import by.training.finaltask.bean.entities.User;
import by.training.finaltask.dao.DaoFactory;
import by.training.finaltask.dao.daoimpl.DocumentDaoImpl;
import by.training.finaltask.dao.daoimpl.StudentDaoImpl;
import by.training.finaltask.dao.daoimpl.UserDaoImpl;
import by.training.finaltask.dao.exception.DaoException;
import by.training.finaltask.service.BaseService;
import by.training.finaltask.service.UserService;
import by.training.finaltask.service.excpetion.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceImpl extends BaseService implements UserService {
    private static final Logger serviceLog = LogManager.getLogger("ServiceLog");

    @Override
    public User login(String login, String password) throws ServiceException {
        User user = null;
        if (login != null && password != null) {
            try {
                UserDaoImpl userDao = DaoFactory.getInstance().getUserDao();
                transaction.init(userDao);
                user = userDao.findByLoginPass(login, password);
            } catch (DaoException e) {
                throw new ServiceException(e);
            } finally {
                transaction.endTransaction();
            }
        }
        return user;
    }

    @Override
    public boolean deleteUser(Integer id) throws ServiceException {
        serviceLog.info("id = " + id);
        UserDaoImpl userDao = DaoFactory.getInstance().getUserDao();
        StudentDaoImpl studentDao = DaoFactory.getInstance().getStudentDao();
        DocumentDaoImpl documentDao = DaoFactory.getInstance().getDocumentDao();
        transaction.init(userDao, studentDao, documentDao);
        if (id == null || id < 0) {
            serviceLog.info("incorrect id");
            return false;
        }
        try {
            User user = userDao.findById(id);
            if (user == null || user.getRole() == Role.ADMINISTRATOR) {
                serviceLog.info(" user is null or administrator");
                return false;
            }
            List<Document> documents = documentDao.findByUserId(id);
            if (documents.size() > 0) {
                if (!documentDao.deleteUserReferences(id)) {
                    serviceLog.info("cannot delete references in user's documents");
                    return false;
                }
            }
            if (studentDao.findById(id) == null || studentDao.delete(id)) {
                return userDao.delete(id);
            }
            transaction.rollback();
            return false;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public List<User> viewUsers() throws ServiceException {
        UserDaoImpl userDao = DaoFactory.getInstance().getUserDao();
        transaction.init(userDao);
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public User findUser(Integer id) throws ServiceException {
        UserDaoImpl userDao = DaoFactory.getInstance().getUserDao();
        transaction.init(userDao);
        try {
            if (id != null && id > 0) {
                return userDao.findById(id);
            } else {
                serviceLog.info("incorrect id");
                return null;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public boolean updateUser(User user) throws ServiceException {
        if (user != null && user.getId() != null && user.getRole() != null && user.getRole() != Role.ADMINISTRATOR && user.getLogin() != null) {
            UserDaoImpl userDao = DaoFactory.getInstance().getUserDao();
            transaction.init(userDao);
            try {
                if (userDao.findById(user.getId()) == user) {
                    serviceLog.info("same user for update in db");
                    return true;
                }
                return userDao.update(user);
            } catch (DaoException e) {
                transaction.rollback();
                throw new ServiceException(e);
            } finally {
                transaction.endTransaction();
            }
        } else {
            serviceLog.info("incorrect user");
            return false;
        }
    }
}
