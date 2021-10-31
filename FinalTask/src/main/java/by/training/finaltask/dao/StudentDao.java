package by.training.finaltask.dao;

import by.training.finaltask.bean.entities.Student;
import by.training.finaltask.dao.exception.DaoException;
import by.training.finaltask.service.excpetion.ServiceException;

import java.util.List;

public interface StudentDao extends Dao<Integer, Student> {
    List<Student> findDeanStudent(Integer deanId) throws DaoException;
    boolean isUniqueMail(String mail) throws ServiceException;
}
