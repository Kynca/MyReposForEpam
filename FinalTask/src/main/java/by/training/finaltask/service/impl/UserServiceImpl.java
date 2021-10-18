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
    private static final Logger debugLog = LogManager.getLogger("DebugLog");

    @Override
    public boolean registration(User user) throws ServiceException {
        if (user != null) {
            try {
                UserDaoImpl userDao = DaoFactory.getInstance().getUserDao();
                transaction.init(userDao);
                return userDao.create(user);
            } catch (DaoException e) {
                transaction.rollback();
                throw new ServiceException();
            } finally {
                transaction.endTransaction();
            }
        }
        return false;
    }

    @Override
    public User login(String login, String password) throws ServiceException {
        debugLog.debug("in service");
        User user = null;
        if (login != null && password != null) {
            try {
                debugLog.debug("in service1");
                UserDaoImpl userDao = DaoFactory.getInstance().getUserDao();
                transaction.init(userDao);
                debugLog.debug("service done0");
                user = userDao.findByLoginPass(login, password);
            } catch (DaoException e) {
                debugLog.debug("error in service");
                throw new ServiceException(e);
            } finally {
                transaction.endTransaction();
            }
        }
        debugLog.debug("service done");
        return user;
    }

    @Override
    public boolean changePass(String login, String password, String newPassword) throws ServiceException {
        if (login != null && password != null && newPassword != null) {
            UserDaoImpl userDao = DaoFactory.getInstance().getUserDao();
            transaction.init(userDao);
            try {
                User user = userDao.findByLoginPass(login, password);
                if (user != null) {
                    return userDao.update(user);
                } else {
                    return false;
                }
            } catch (DaoException e) {
                transaction.rollback();
                throw new ServiceException(e);
            } finally {
                transaction.endTransaction();
            }
        } else {
            //TODO log
            return false;
        }
    }

    @Override
    public boolean deleteUser(Integer id) throws ServiceException {
        debugLog.debug("in service delete" + id);
        UserDaoImpl userDao = DaoFactory.getInstance().getUserDao();
        StudentDaoImpl studentDao = DaoFactory.getInstance().getStudentDao();
        DocumentDaoImpl documentDao = DaoFactory.getInstance().getDocumentDao();
        transaction.init(userDao, studentDao, documentDao);
        debugLog.debug("init transaction");
        if(id == null){
            return false;
        }
        try {
            User user = userDao.findById(id);
            if(user == null || user.getRole() == Role.ADMINISTRATOR){
                return false;
            }
            List<Document> documents = documentDao.findByUserId(id);
            if (documents.size() > 0) {
                    if (!documentDao.deleteUserReferences(id)) {
                        debugLog.debug("cannot update doc ");
                        return false;
                    }
            }
            debugLog.debug("checked documents");
            if (studentDao.findById(id) == null || studentDao.delete(id)) {
                debugLog.debug("deleted student");
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
            if (id != null) {
                return userDao.findById(id);
            } else {
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
        debugLog.debug("in user service update " + user);
        if (user != null && user.getId() != null && user.getRole() != null && user.getRole() != Role.ADMINISTRATOR && user.getLogin() != null) {
            UserDaoImpl userDao = DaoFactory.getInstance().getUserDao();
            transaction.init(userDao);
            debugLog.debug("init transaction");
            try {
                if(userDao.findById(user.getId()) == user){
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
            debugLog.debug("not updated");
            return false;
        }
    }
}
