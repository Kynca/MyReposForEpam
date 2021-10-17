package by.training.finaltask.dao;

import by.training.finaltask.bean.entities.Student;
import by.training.finaltask.dao.exception.DaoException;

import java.util.List;

public interface StudentDao extends Dao<Integer, Student> {
    List<Student> findDeanStudent(Integer deanId) throws DaoException;
}
