package by.training.finaltask.service.impl;

import by.training.finaltask.bean.entities.Mark;
import by.training.finaltask.dao.DaoFactory;
import by.training.finaltask.dao.daoimpl.MarkDaoImpl;
import by.training.finaltask.dao.exception.DaoException;
import by.training.finaltask.service.BaseService;
import by.training.finaltask.service.MarkService;
import by.training.finaltask.service.excpetion.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MarkServiceImpl extends BaseService implements MarkService {

    private static final Logger serviceLog = LogManager.getLogger("ServiceLog");

    @Override
    public List<Mark> viewMarks(Integer id) throws ServiceException {
        serviceLog.info("id = " + id);
        MarkDaoImpl markDao = DaoFactory.getInstance().getMarkDao();
        transaction.init(markDao);
        try {
            if (id != null && id > 0) {
                return markDao.findByStudentId(id);
            }
            serviceLog.info("incorrect id");
            return new ArrayList<Mark>();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }finally {
            transaction.endTransaction();
        }
    }

    @Override
    public boolean setRate(Mark mark) throws ServiceException {
        serviceLog.info("marks to set = " + mark);
        try {
            if (mark != null && mark.getRate() != null && mark.getRate() > 0 && mark.getRate() <= 10) {
                MarkDaoImpl markDao = DaoFactory.getInstance().getMarkDao();
                transaction.init(markDao);
                return markDao.update(mark);
            }
            serviceLog.info("incorrect marks");
            return false;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        }finally {
            transaction.endTransaction();
        }
    }

    //TODO invoke
    @Override
    public boolean createMark(Mark mark) throws ServiceException {
        try {
            if (mark != null) {
                MarkDaoImpl markDao = DaoFactory.getInstance().getMarkDao();
                transaction.init(markDao);
                return markDao.create(mark);
            }
            return false;
        } catch (DaoException e) {
            transaction.rollback();
            throw  new ServiceException(e);
        }finally {
            transaction.endTransaction();
        }
    }
}
