package by.training.finaltask.service.impl;

import by.training.finaltask.bean.entities.Dean;
import by.training.finaltask.bean.entities.University;
import by.training.finaltask.dao.DaoFactory;
import by.training.finaltask.dao.UniversityDao;
import by.training.finaltask.dao.daoimpl.DeanDaoImpl;
import by.training.finaltask.dao.daoimpl.UniversityDaoImpl;
import by.training.finaltask.dao.exception.DaoException;
import by.training.finaltask.service.DeanService;
import by.training.finaltask.service.BaseService;
import by.training.finaltask.service.excpetion.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeanServiceImpl extends BaseService implements DeanService {

    private static final Logger debugLog = LogManager.getLogger("DebugLog");

    @Override
    public boolean deleteDean(Integer id) throws ServiceException {
        DeanDaoImpl deanDao = DaoFactory.getInstance().getDeanDao();
        transaction.init(deanDao);
        try {
            if (id != null && id > 0) {
                Dean dean = deanDao.findById(id);
                if (dean != null) {
                    return deanDao.delete(id);
                }
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
    public boolean updateDean(Dean dean) throws ServiceException {

        DeanDaoImpl deanDao = DaoFactory.getInstance().getDeanDao();
        UniversityDao universityDao = DaoFactory.getInstance().getUniversityDao();
        transaction.init(deanDao);
        if (dean != null && dean.getId() != null && check(dean, universityDao)) {
            try {
                return deanDao.update(dean);
            } catch (DaoException e) {
                transaction.rollback();
                throw new ServiceException(e);
            } finally {
                transaction.endTransaction();
            }
        }
        return false;
    }

    @Override
    public boolean create(Dean dean) throws ServiceException {
        debugLog.debug("in create");
        DeanDaoImpl deanDao = DaoFactory.getInstance().getDeanDao();
        UniversityDaoImpl universityDao = DaoFactory.getInstance().getUniversityDao();
        transaction.init(deanDao, universityDao);
        debugLog.debug("init transaction");
        if (dean != null && check(dean, universityDao)) {
            try {
                return deanDao.create(dean);
            } catch (DaoException e) {
                transaction.rollback();
                throw new ServiceException(e);
            } finally {
                transaction.endTransaction();
            }
        }
        return false;
    }

    @Override
    public List<Dean> viewDeans() throws ServiceException {
        DeanDaoImpl deanDao = DaoFactory.getInstance().getDeanDao();
        transaction.init(deanDao);
        try {
            return deanDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public Dean findDean(Integer id) throws ServiceException {
        if (id != null && id > 0) {
            DeanDaoImpl deanDao = DaoFactory.getInstance().getDeanDao();
            transaction.init(deanDao);
            try {
                return deanDao.findById(id);
            } catch (DaoException e) {
                throw new ServiceException(e);
            } finally {
                transaction.endTransaction();
            }
        }
        return null;
    }

    @Override
    public Map<Dean, List<University>> findDeanUniversities(Integer id) throws ServiceException {
        if (id != null && id > 0) {
            DeanDaoImpl deanDao = DaoFactory.getInstance().getDeanDao();
            UniversityDaoImpl universityDao = DaoFactory.getInstance().getUniversityDao();
            transaction.init(deanDao, universityDao);
            try {
                Dean dean = deanDao.findById(id);
                if (dean != null) {
                    List<University> universities = universityDao.findAll();
                    Map<Dean, List<University>> map = new HashMap<>();
                    map.put(dean, universities);
                    return map;
                } else {
                    transaction.rollback();
                    return null;
                }
            } catch (DaoException e) {
                transaction.rollback();
            }
        }
        return null;
    }

    @Override
    public List<University> findUniversities() throws ServiceException {
        UniversityDaoImpl universityDao = DaoFactory.getInstance().getUniversityDao();
        transaction.init(universityDao);
        try {
           return universityDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }finally {
            transaction.endTransaction();
        }
    }

    private boolean check(Dean dean, UniversityDao universityDao) throws ServiceException {
        debugLog.debug("checking dean");
        Integer id = dean.getId();
        Long number = dean.getPhoneNumber();
        int length = String.valueOf(number).length();
        String address = dean.getAddress();
        Integer universityId = dean.getUniversityId();

        debugLog.debug(id + " " + length + " " + address, " " + universityId);

        if (address == null && length >= 10 && length <= 13) {
            return false;
        }
        debugLog.debug("check 1 pass");
        try {
            if (universityId == null && universityDao.findById(universityId) == null) {
                return false;
            }
            debugLog.debug("check 2 pass");
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return true;
    }
}
