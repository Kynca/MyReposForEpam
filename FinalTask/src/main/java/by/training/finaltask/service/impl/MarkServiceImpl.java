package by.training.finaltask.service.impl;

import by.training.finaltask.bean.entities.Mark;
import by.training.finaltask.dao.DaoFactory;
import by.training.finaltask.dao.daoimpl.MarkDaoImpl;
import by.training.finaltask.dao.exception.DaoException;
import by.training.finaltask.service.BaseService;
import by.training.finaltask.service.MarkService;
import by.training.finaltask.service.excpetion.ServiceException;

import java.util.List;

public class MarkServiceImpl extends BaseService implements MarkService {

    @Override
    public List<Mark> viewMarks(Integer id) throws ServiceException {
        MarkDaoImpl markDao = DaoFactory.getInstance().getMarkDao();
        transaction.init(markDao);
        try {
            if (id != null && id > 0) {
                return markDao.findByStudentId(id);
            }
            return null;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }finally {
            transaction.endTransaction();
        }
    }

    @Override
    public boolean setRate(Mark mark) throws ServiceException {
        try {
            if (mark != null && mark.getRate() != null && mark.getRate() > 0 && mark.getRate() <= 10) {
                MarkDaoImpl markDao = DaoFactory.getInstance().getMarkDao();
                transaction.init(markDao);
                return markDao.update(mark);
            }
            return false;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        }finally {
            transaction.endTransaction();
        }
    }

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
