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

    private static final Logger serviceLog = LogManager.getLogger("ServiceLog");

    @Override
    public boolean deleteDean(Integer id) throws ServiceException {
        DeanDaoImpl deanDao = DaoFactory.getInstance().getDeanDao();
        transaction.init(deanDao);
        serviceLog.info("id = " + id);
        try {
            if (id != null && id > 0) {
                Dean dean = deanDao.findById(id);
                if (dean != null) {
                    return deanDao.delete(id);
                } else {
                    transaction.rollback();
                    throw new IllegalArgumentException("dean with" + id + "not found");
                }
            } else {
                transaction.rollback();
                throw new IllegalArgumentException("incorrect id");
            }
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public boolean updateDean(Dean dean) throws ServiceException {
        serviceLog.info("get Dean for update = " + dean);
        DeanDaoImpl deanDao = DaoFactory.getInstance().getDeanDao();
        UniversityDao universityDao = DaoFactory.getInstance().getUniversityDao();
        transaction.init(deanDao);
        if (dean != null && check(dean, universityDao) && dean.getId() != null) {
            serviceLog.info("dean checked");
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
        DeanDaoImpl deanDao = DaoFactory.getInstance().getDeanDao();
        UniversityDaoImpl universityDao = DaoFactory.getInstance().getUniversityDao();
        transaction.init(deanDao, universityDao);
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
        }else{
            throw new IllegalArgumentException("incorrect id");
        }
    }

    @Override
    public Map<Dean, List<University>> findDeanUniversities(Integer id) throws ServiceException {
        serviceLog.info("id = " + id);
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
                    serviceLog.info("dean = null");
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
        } finally {
            transaction.endTransaction();
        }
    }

    private boolean check(Dean dean, UniversityDao universityDao) throws ServiceException {
        serviceLog.debug("check");

        Integer id = dean.getId();
        Long number = dean.getPhoneNumber();
        int length = String.valueOf(number).length();
        String address = dean.getAddress();
        Integer universityId = dean.getUniversityId();

        serviceLog.debug(id + " " + length + " " + address, " " + universityId);

        if (address == null || dean.getFaculty() == null) {
            throw new IllegalArgumentException("incorrect data");
        }
        if(!(length >= 10 && length <= 13)){
            throw new IllegalArgumentException("incorrect phone number");
        }
        serviceLog.debug("check 1 pass");
        try {
            if (universityId == null || universityDao.findById(universityId) == null) {
                throw new IllegalArgumentException("university not found");
            }
            serviceLog.debug("check 2 pass");
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        serviceLog.info("dean checked and meet to condition");
        return true;
    }
}
